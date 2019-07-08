package com.aib.base.mvp;

/**
 * Created by zhangdeming on 2017/9/14.
 */

public class FramePresenter<V> implements BasePresenter<V> {

    protected V v;

    @Override
    public void attachView(V view) {
        v = view;
    }

    @Override
    public void detachView() {
        v = null;
    }
}
