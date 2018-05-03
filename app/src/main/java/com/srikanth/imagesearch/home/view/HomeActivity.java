package com.srikanth.imagesearch.home.view;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.srikanth.imagesearch.R;
import com.srikanth.imagesearch.common.base.BaseActivity;
import com.srikanth.imagesearch.common.utils.NetworkUtils;
import com.srikanth.imagesearch.home.adapters.ImageGridAdapter;
import com.srikanth.imagesearch.home.model.ImageListResponse;
import com.srikanth.imagesearch.home.presenter.HomePresenter;
import com.srikanth.imagesearch.home.presenter.HomePresenterImpl;

public class HomeActivity extends BaseActivity implements HomeView {
    private EditText mSearchEditText;
    private GridView mImageGridView;
    private HomePresenter mHomePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initViews();
    }

    private void initViews() {
        mHomePresenter = new HomePresenterImpl(this);
        mImageGridView = findViewById(R.id.grid_view);
        mSearchEditText = findViewById(R.id.edt_home_search);
        mSearchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (NetworkUtils.hasInternetConnectionOtherwiseShowToast(HomeActivity.this)) {
                        mHomePresenter.fetchImagesFromServer(getApplication(), mSearchEditText.getText().toString());
                    }
                    return true;
                }
                return false;
            }
        });

    }

    @Override
    public void showProgressDialog() {
        super.showProgressDialog();
    }

    @Override
    public void hideProgressDialog() {
        super.hideProgressDialog();
    }

    @Override
    public void notifyAdapter(ImageListResponse response) {
        mImageGridView.setAdapter(new ImageGridAdapter(this, response.imageList));
    }
}