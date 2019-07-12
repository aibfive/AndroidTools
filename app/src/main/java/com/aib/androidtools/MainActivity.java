package com.aib.androidtools;

import android.view.View;

import com.aib.androidtools.page.decoration.DecorationActivity;
import com.aib.base.frame.FrameActivity;

public class MainActivity extends FrameActivity {

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    /**
     * RecyclerView的子项装饰
     * @param view
     */
    public void onDecorationClick(View view){
        gotoActivityNotClose(DecorationActivity.class, null);
    }


    public void onSwipeBackClick(View view){

    }

}
