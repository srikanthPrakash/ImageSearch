package com.srikanth.imagesearch.common.network;

import android.app.Application;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.srikanth.imagesearch.common.managers.AuthManager;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class GsonRequest<T> extends JsonRequest<T> {
    private static final int TIMEOUT_MS = 60 * 1000;

    private final Gson mGson;
    private final Class<T> mClassOfT;
    private final Map<String, String> mHeaders;
    private final Listener<T> mListener;
    private final ErrorListener mErrListerner;
    private final Application mApp;
    private Map<String, String> mParameters = null;

    /**
     * Make a GET request and return a parsed object from JSON.
     *
     * @param builder request builder
     */
    private GsonRequest(Application app, GsonRequestBuilder builder) {
        super(builder.mMethod, builder.mUrl, builder.mRequestBody,
                builder.mListener, builder.mErrorListener);
        mApp = app;
        mClassOfT = builder.mClassOfT;
        mHeaders = builder.mHeaders;
        mParameters = builder.mParameters;
        mListener = builder.mListener;
        mErrListerner = builder.mErrorListener;
        mGson = new Gson();
        Log.v("HTTP", "Request Url " + builder.mUrl);
        AuthManager.getInstance().setAuthToken(app, mHeaders);
        setRetryPolicy(new DefaultRetryPolicy(TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

    }

    @Override
    public void deliverError(VolleyError error) {
        super.deliverError(error);
        mErrListerner.onErrorResponse(error);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            T result;
            String json = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            Log.v("HTTP", "parseNetworkResponse: " + json);
            result = mGson.fromJson(json, mClassOfT);
            result = postProcess(result);
            return Response.success(result,
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

    protected T postProcess(T response) {
        return response;
    }

    public static class GsonRequestBuilder<T> extends BaseRequestBuilder<String, T> {
        private String mRequestBody;
        private Application mApp;

        public GsonRequestBuilder(Application app, String url, int method, Listener<T> listener,
                                  ErrorListener errorListener) {
            super(url, method, listener, errorListener);
            mApp = app;
        }

        @Override
        public GsonRequestBuilder requestBody(String requestBody) {
            mRequestBody = requestBody;
            return this;
        }

        @Override
        public GsonRequest build() {
            return new GsonRequest(mApp, this);
        }
    }

    @Override
    public String getBodyContentType() {
        return "text";
    }

    @Override
    public String getPostBodyContentType() {
        return super.getPostBodyContentType();
    }
}