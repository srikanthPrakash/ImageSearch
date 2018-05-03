package com.srikanth.imagesearch.common.managers;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.srikanth.imagesearch.common.dto.BaseResponse;

public abstract class BaseManager {
    private static final String TAG = BaseManager.class.getSimpleName();

    /**
     * This is a generic method which will be called once data gets downloaded
     * from server successfully. It uses UiCallbacks interface to notify ui
     * classes to update ui controls.
     *
     * @param callback API callback
     */
    protected static <T> Response.Listener<T> getSuccessListener(
            final UiCallbacks<T> callback) {
        return new Response.Listener<T>() {

            @Override
            public void onResponse(T response) {
                if (callback != null) {
                    if (response != null) {
                        if (response instanceof BaseResponse) {
                            callback.onDownloadSuccess(response);
                        } else {
                            callback.onDownloadFail(null);
                        }
                    } else {
                        callback.onDownloadFail(null);
                    }
                }
            }
        };
    }


    /**
     * This is a generic method which will be called once data gets failed due
     * to some reasons.
     *
     * @param callback API callback
     */

    protected static Response.ErrorListener getErrorListener(
            final UiCallbacks callback) {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (callback != null) {
                    try {
                        if (error != null && error.networkResponse != null) {
                            String responseBody = new String(error.networkResponse.data, "utf-8");
                            Gson gson = new Gson();
                            BaseResponse response = gson.fromJson(responseBody, BaseResponse.class);
                            callback.onDownloadFail(null);
                        }
                        Log.d(TAG, "Inside try");
                        callback.onDownloadFail(null);
                    } catch (Exception e) {
                        Log.d(TAG, "Inside catch:"+e.getLocalizedMessage());
                        callback.onDownloadFail(null);
                    }

                }
            }
        };
    }

    public interface UiCallbacks<T> {
        void onDownloadSuccess(T response);

        void onDownloadFail(String errorMsg);
    }
}
