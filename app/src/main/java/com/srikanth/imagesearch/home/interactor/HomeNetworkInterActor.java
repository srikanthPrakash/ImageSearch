package com.srikanth.imagesearch.home.interactor;

import android.app.Application;

import com.android.volley.Request;
import com.srikanth.imagesearch.common.managers.BaseManager;
import com.srikanth.imagesearch.common.network.GsonRequest;
import com.srikanth.imagesearch.common.network.VolleyHelper;
import com.srikanth.imagesearch.common.utils.UrlUtils;
import com.srikanth.imagesearch.home.model.ImageListResponse;

public class HomeNetworkInterActor extends BaseManager {

    public static void getImageListResponse(final Application app, String tag, final String queryString,
                                            final UiCallbacks<ImageListResponse> callback) {
        GsonRequest.GsonRequestBuilder<ImageListResponse> requestBuilder = new
                GsonRequest.GsonRequestBuilder<>(app, UrlUtils.getImageListUrl(app, queryString),
                Request.Method.GET, getSuccessListener(callback), getErrorListener(callback));
        requestBuilder.classOfT(ImageListResponse.class);

        GsonRequest request = requestBuilder.build();
        request.setTag(tag);
        VolleyHelper.getInstance(app).addToRequestQueue(request);
    }
}