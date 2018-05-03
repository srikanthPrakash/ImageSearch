package com.srikanth.imagesearch.home.presenter;

import android.app.Application;
import android.widget.Toast;

import com.srikanth.imagesearch.R;
import com.srikanth.imagesearch.common.managers.BaseManager;
import com.srikanth.imagesearch.home.model.ImageListResponse;
import com.srikanth.imagesearch.home.interactor.HomeNetworkInterActor;
import com.srikanth.imagesearch.home.view.HomeView;

/**
 * Created by srikanth on 3/5/18.
 */

public class HomePresenterImpl implements HomePresenter {
    private final HomeView mHomeView;

    public HomePresenterImpl(final HomeView homeView) {
        mHomeView = homeView;
    }

    @Override
    public void fetchImagesFromServer(final Application context, final String queryText) {
        mHomeView.showProgressDialog();
        HomeNetworkInterActor.getImageListResponse(context, getClass().getName(), queryText,
                new BaseManager.UiCallbacks<ImageListResponse>() {
                    @Override
                    public void onDownloadSuccess(ImageListResponse response) {
                        mHomeView.hideProgressDialog();
                        if (response != null) {
                            if (response.imageList != null && response.imageList.size() == 0) {
                                Toast.makeText(context, context.getString(R.string.no_results_note),
                                        Toast.LENGTH_SHORT).show();
                            }
                            mHomeView.notifyAdapter(response);
                        }
                    }

                    @Override
                    public void onDownloadFail(String errorMsg) {
                        mHomeView.hideProgressDialog();
                        Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
