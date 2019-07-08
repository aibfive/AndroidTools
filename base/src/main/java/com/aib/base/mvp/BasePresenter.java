package com.aib.base.mvp;

/**
 * Created by zhangdeming on 2017/8/18.
 */

public interface BasePresenter<V> {

    void attachView(V view);

    void detachView();
}
