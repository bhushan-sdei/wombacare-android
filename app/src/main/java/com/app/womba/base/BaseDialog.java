package com.app.womba.base;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.app.womba.R;
import com.google.android.material.snackbar.Snackbar;

abstract public class BaseDialog extends Dialog {
    Snackbar mSnackbar;
    Context mContext;
    private int mWidth, mHeight;

    protected BaseDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public BaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public BaseDialog(@NonNull Context context) {
        super(context);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        WindowManager.LayoutParams windowManager = this.getWindow().getAttributes();
        setContentView(getContentView());
        mContext = getContext();
        getDefaults();
        onCreateStuff();
        windowManager.width = WindowManager.LayoutParams.MATCH_PARENT;
        windowManager.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(windowManager);
    }

    protected abstract void onCreateStuff();

    public abstract int getContentView();

    private void getDefaults() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) mContext.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        mWidth = displayMetrics.widthPixels;
        mHeight = displayMetrics.heightPixels;
        Log.e("Height = ", mHeight + " width " + mWidth);
    }

    protected void showInternetAlert(@NonNull View view) {
        mSnackbar = Snackbar.make(view, R.string.errorInternet, Snackbar.LENGTH_SHORT);
        mSnackbar.show();
    }

}
