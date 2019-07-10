package com.aib.androidtools;

import android.view.View;

import com.aib.base.frame.FrameActivity;
import com.aib.base.swipeback.SwipeBackActivity;

public class DecorationActivity extends SwipeBackActivity {

    @Override
    protected int layoutId() {
        return R.layout.activity_decoration;
    }

    public void onVerticalDecorationClick(View view){
        gotoActivityNotClose(VerticalDecorationActivity.class, null);
    }

    public void onHorizontalDecorationClick(View view){
        gotoActivityNotClose(HorizontalDecorationActivity.class, null);
    }

}
