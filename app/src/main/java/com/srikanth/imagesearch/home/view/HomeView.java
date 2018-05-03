package com.srikanth.imagesearch.home.view;

import com.srikanth.imagesearch.home.model.ImageListResponse;

public interface HomeView {

    void showProgressDialog();
    void hideProgressDialog();
    void notifyAdapter(ImageListResponse response);
}
