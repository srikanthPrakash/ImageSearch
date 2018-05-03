package com.srikanth.imagesearch.common.utils;

import android.content.Context;

import com.srikanth.imagesearch.R;

public class UrlUtils {
    public static String appendBaseUrl(Context ctx, String url) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ctx.getString(R.string.base_url)).append(url);
        return stringBuilder.toString();
    }

    public static String getImageListUrl(Context context, String queryString) {
        StringBuilder stringBuilder = new StringBuilder(appendBaseUrl(context, context.getString(R.string.get_image_list_url)));
        stringBuilder.append("/?key=")
                .append(context.getString(R.string.api_key))
                .append("&image_type=photo")
                .append("&q=")
                .append(queryString.trim().replace(" ",""));
        return stringBuilder.toString();
    }
}
