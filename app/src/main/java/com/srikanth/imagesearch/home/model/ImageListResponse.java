package com.srikanth.imagesearch.home.model;

import com.google.gson.annotations.SerializedName;
import com.srikanth.imagesearch.common.dto.BaseResponse;

import java.util.ArrayList;

public class ImageListResponse extends BaseResponse {
    @SerializedName("hits")
    public ArrayList<ImageItem> imageList;


    public static class ImageItem{
        @SerializedName("previewURL")
        public String imageUrl;
    }
}