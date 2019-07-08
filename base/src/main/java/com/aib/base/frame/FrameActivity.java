package com.aib.base.frame;

import android.os.Bundle;
import android.view.View;

import org.greenrobot.eventbus.EventBus;

/**
 * @author zhangdeming
 * @date 创建时间 2016/9/26
 * @description 基础的框架类
 */
public class FrameActivity extends BaseActivity {

    private boolean isRegister = false;

    @Override
    protected void activityConfigure() {

    }

    @Override
    protected int layoutId() {
        return 0;
    }

    @Override
    protected void receiveDataFromPreActivity(Bundle data) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initComponent() {
        if (isRegister) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void initListener() {

    }

    public void registerEventBus() {
        isRegister = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isRegister) {
            EventBus.getDefault().unregister(this);
        }
    }

    public void onReturnClick(View v) {
        finish();
    }
}
