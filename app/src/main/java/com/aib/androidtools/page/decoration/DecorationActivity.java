package com.aib.androidtools.page.decoration;

import android.view.View;

import com.aib.androidtools.R;
import com.aib.base.frame.FrameActivity;

public class DecorationActivity extends FrameActivity {

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
