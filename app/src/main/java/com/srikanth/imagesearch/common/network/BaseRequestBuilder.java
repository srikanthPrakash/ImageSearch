package com.srikanth.imagesearch.common.network;

import com.android.volley.Request;
import com.android.volley.Response;

import java.util.Map;

public abstract class BaseRequestBuilder<I, O> {
    protected final int mMethod;
    protected final String mUrl;
    protected final Response.Listener<O> mListener;
    protected final Response.ErrorListener mErrorListener;
    protected Map<String, String> mHeaders;
    protected Class<O> mClassOfT;
    public Map<String, String> mParameters;

    public BaseRequestBuilder(String url, int method, Response.Listener<O> listener,
                              Response.ErrorListener errorListener) {
        mUrl = url;
        mMethod = method;
        mListener = listener;
        mErrorListener = errorListener;
    }

    public BaseRequestBuilder classOfT(Class<O> classOfT) {
        mClassOfT = classOfT;
        return this;
    }

    public BaseRequestBuilder headers(Map<String, String> header) {
        mHeaders = header;
        return this;
    }

    public BaseRequestBuilder parameters(Map<String, String> parameter) {
        mParameters = parameter;
        return this;
    }

    public BaseRequestBuilder requestBody(I requestBody) { return null; }

    public abstract Request build();
}