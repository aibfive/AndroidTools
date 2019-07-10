package com.aib.androidtools;

import android.content.Intent;
import android.view.View;

import com.aib.base.frame.FrameActivity;
import com.aib.base.swipeback.SwipeBackActivity;

public class MainActivity extends SwipeBackActivity {

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

    /**
     * ItemDecoration
     * @param view
     */
    public void onDecorationClick(View view){
        gotoActivityNotClose(DecorationActivity.class, null);
    }

}
