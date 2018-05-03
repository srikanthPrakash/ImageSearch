package com.srikanth.imagesearch.home.presenter;

import android.app.Application;

public interface HomePresenter {
    void fetchImagesFromServer(final Application context, final String queryText);
}
