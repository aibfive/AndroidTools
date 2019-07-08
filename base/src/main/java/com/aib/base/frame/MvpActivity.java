package com.aib.base.frame;

import com.aib.base.mvp.FramePresenter;
import com.aib.base.swipeback.SwipeBackActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangdeming
 * @date 2018/6/21
 * @description MVP activity的基础类
 */

public class MvpActivity extends SwipeBackActivity {

    protected List<FramePresenter> presenters = new ArrayList<>();

    protected void addPresenter(FramePresenter presenter) {
        presenters.add(presenter);
        presenter.attachView(this);
    }


    @Override
    protected void initComponent() {
        super.initComponent();
        for (FramePresenter p : presenters) {
            p.attachView(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (FramePresenter p : presenters) {
            p.detachView();
        }
    }

}
