package com.srikanth.imagesearch.home.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.android.volley.toolbox.NetworkImageView;
import com.srikanth.imagesearch.R;
import com.srikanth.imagesearch.common.network.VolleyHelper;
import com.srikanth.imagesearch.home.model.ImageListResponse.ImageItem;

import java.util.ArrayList;


public class ImageGridAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<ImageItem> mImageList;
    private static final int IMAGE_WIDTH = 300;
    private static final int IMAGE_HEIGHT = 330;

    // Constructor
    public ImageGridAdapter(Context context, ArrayList<ImageItem> imageList) {
        mContext = context;
        mImageList = imageList;
    }

    @Override
    public int getCount() {
        return mImageList.size();
    }

    @Override
    public Object getItem(int position) {
        return mImageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NetworkImageView imageView = new NetworkImageView(mContext);
        imageView.setImageUrl(mImageList.get(position).imageUrl, VolleyHelper.getInstance(mContext).getImageLoader());
        imageView.setDefaultImageResId(R.drawable.ic_launcher_background);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(IMAGE_WIDTH, IMAGE_HEIGHT));
        return imageView;
    }

}