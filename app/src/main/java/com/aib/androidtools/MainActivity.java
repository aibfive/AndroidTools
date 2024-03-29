package com.aib.androidtools;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.aib.androidtools.page.MainAActivity;
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
        gotoActivityNotClose(MainAActivity.class, null);
        //设置切换动画

        overridePendingTransition(R.anim.in_from_down, R.anim.out_to_up);
    }

    private final String TAG = getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("GG", TAG+"-->onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("GG", TAG+"-->onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("GG", TAG+"-->onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("GG", TAG+"-->onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("GG", TAG+"-->onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("GG", TAG+"-->onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("GG", TAG+"-->onDestroy");
    }

}
