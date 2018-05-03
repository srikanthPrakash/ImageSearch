package com.srikanth.imagesearch.common.network;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RequestQueue.RequestFilter;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.HurlStack.UrlRewriter;
import com.android.volley.toolbox.ImageLoader;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

/**
 * This class contains utility methods to interact with Volley library
 *
 */
public class VolleyHelper {
    private static final int THREAD_POOL_SIZE = 4;
    private static VolleyHelper sInstance;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    private VolleyHelper(Context context) {
        mRequestQueue = getRequestQueue(context.getApplicationContext());

        mImageLoader = new ImageLoader(mRequestQueue, new LruBitmapCache(context));

    }

    public static synchronized VolleyHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new VolleyHelper(context);
        }
        return sInstance;
    }

    public static void clearInstance() {
        sInstance = null;
    }

    /**
     * Initialize volley request queue
     *
     * @param ctx Application context
     * @return RequestQueue
     */
    public RequestQueue getRequestQueue(Context ctx) {
        if (mRequestQueue == null) {
            DiskBasedCache cache = new DiskBasedCache(ctx.getFilesDir(),
                    10 * 1024 * 1024);
            try {
                HurlStack hurlStack;
                SSLContext sslCtx = SSLContext.getInstance("SSL");
                sslCtx.init(null, new TrustManager[]{new SSLTrustManager()}, null);
                SSLSocketFactory socketFactory = sslCtx.getSocketFactory();
                HttpsURLConnection.setDefaultSSLSocketFactory(socketFactory);
                HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {

                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });
                hurlStack = new HurlStack(new UrlRewriter() {

                    @Override
                    public String rewriteUrl(String originalUrl) {
                        return originalUrl;
                    }
                }, socketFactory);
                mRequestQueue = new RequestQueue(cache, new BasicNetwork(
                        hurlStack), THREAD_POOL_SIZE);
                mRequestQueue.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mRequestQueue;
    }

    public void cancelRequestsWithTag(String tag) {
        if (!TextUtils.isEmpty(tag)) {
            mRequestQueue.cancelAll(tag);
        }
    }

    public <T> void addToRequestQueue(Request<T> req) {
        mRequestQueue.add(req);
    }

    public void cancelAllRequests() {
        mRequestQueue.cancelAll(new RequestFilter() {
            @Override
            public boolean apply(Request<?> request) {
                return true;
            }
        });
    }


    public ImageLoader getImageLoader() {
        return mImageLoader;
    }

}
