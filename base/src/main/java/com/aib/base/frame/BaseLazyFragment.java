package com.aib.base.frame;

import android.os.Bundle;

/**
 * JituUserAndroid
 *
 * @author lizewu
 * @date 2018/12/3
 */
public abstract class BaseLazyFragment extends FrameFragment {
    private static final String TAG = BaseLazyFragment.class.getSimpleName();
    private boolean isPrepared;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initPrepare();
    }


    /**
     * 第一次onResume中的调用onUserVisible避免操作与onFirstUserVisible操作重复
     */
    private boolean isFirstResume = true;

    @Override
    public void onResume() {
        super.onResume();
        if (isFirstResume) {
            isFirstResume = false;
            return;
        }
        if (getUserVisibleHint()) {
            onUserVisible();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (getUserVisibleHint()) {
            onUserInvisible();
        }
    }

    private boolean isFirstVisible = true;
    private boolean isFirstInvisible = true;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (isFirstVisible) {
                isFirstVisible = false;
                initPrepare();
            } else {
                onUserVisible();
            }
        } else {
            if (isFirstInvisible) {
                isFirstInvisible = false;
                onFirstUserInvisible();
            } else {
                onUserInvisible();
            }
        }
    }

    public synchronized void initPrepare() {
        if (isPrepared) {
            onFirstUserVisible();
        } else {
            isPrepared = true;
        }
    }

    /**
     * 第一次fragment可见（进行初始化工作）
     */
    protected abstract void onFirstUserVisible();

    /**
     * fragment可见（切换回来或者onResume）
     */
    protected void onUserVisible(){

    }

    /**
     * 第一次fragment不可见（不建议在此处理事件）
     */
    protected void onFirstUserInvisible(){

    }

    /**
     * fragment不可见（切换掉或者onPause）
     */
    protected void onUserInvisible(){

    }

}