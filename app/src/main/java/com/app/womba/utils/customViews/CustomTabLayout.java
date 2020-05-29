package com.app.womba.utils.customViews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.material.tabs.TabLayout;

public class CustomTabLayout extends TabLayout {
    private Typeface mTypeface;
    public CustomTabLayout(@NonNull Context context) {
        super(context);
        init();
    }

    public CustomTabLayout(@NonNull Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomTabLayout(@NonNull Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init() {
        mTypeface = Typeface.createFromAsset(getContext()
                .getAssets(), "OpenSans_Semibold.ttf");
    }

    @Override
    public void addTab(@NonNull Tab tab) {
        super.addTab(tab);

        ViewGroup mainView = (ViewGroup) getChildAt(0);
        ViewGroup tabView = (ViewGroup) mainView.getChildAt(tab.getPosition());

        int tabChildCount = tabView.getChildCount();
        for (int i = 0; i < tabChildCount; i++) {
            View tabViewChild = tabView.getChildAt(i);
            if (tabViewChild instanceof TextView) {
                ((TextView) tabViewChild).setTypeface(mTypeface);
            }
        }
    }
}
