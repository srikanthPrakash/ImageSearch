package com.srikanth.imagesearch.common.managers;

import android.app.Application;


import com.srikanth.imagesearch.common.network.VolleyHelper;

import java.util.Map;

public class AuthManager extends BaseManager {
    private static AuthManager sInstance = null;

    private AuthManager() {
    }

    public static synchronized AuthManager getInstance() {
        if (sInstance == null)
            sInstance = new AuthManager();
        return sInstance;
    }

    public void setAuthToken(Application app, Map<String, String> headers) {

        if (headers != null) {
            //TODO: Add headers if required in future
            /*headers.put("authorization",
                    String.format("Basic %s", Base64.encodeToString(
                            String.format("%s:%s", "Username", "Password").getBytes(), Base64.DEFAULT)));*/

        }
    }

}
