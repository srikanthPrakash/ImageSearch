package com.srikanth.imagesearch.common.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.srikanth.imagesearch.R;

public class NetworkUtils {
    public static boolean hasInternetConnectionOtherwiseShowToast(Context context) {
        boolean hasInternet = true;
        if (context != null && !isInternetOn(context)) {
            hasInternet = false;
            Toast.makeText(context, context.getString(R.string.no_network_msg), Toast.LENGTH_LONG).show();
        } else {
            hasInternet = true;
        }
        return hasInternet;
    }

    /**
     * Utility method to check is Internet On
     *
     * @param context :Context
     * @return:Boolean true if Internet is on else returns false
     */
    public static boolean isInternetOn(Context context) {
        if (context != null) {
            ConnectivityManager conn = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = conn.getActiveNetworkInfo();
            if (networkInfo != null) {
                return networkInfo.isConnected();
            }
        }
        return false;
    }
}
