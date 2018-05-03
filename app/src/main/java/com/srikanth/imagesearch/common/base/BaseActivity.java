package com.srikanth.imagesearch.common.base;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;

import com.srikanth.imagesearch.R;

public abstract class BaseActivity extends AppCompatActivity {
    private ProgressDialog mDialog;

    protected void showProgressDialog() {
        mDialog = new ProgressDialog(this);
        mDialog.setMessage(getString(R.string.loading));
        mDialog.show();
    }

    protected void hideProgressDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }
}
