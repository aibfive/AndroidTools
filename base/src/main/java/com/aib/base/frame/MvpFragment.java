package com.aib.base.frame;

import com.aib.base.mvp.FramePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/26.
 */

public abstract class MvpFragment<P extends FramePresenter> extends FrameFragment {


    protected List<FramePresenter> presenters = new ArrayList<>();


    protected void addPresenter(FramePresenter presenter) {
        presenters.add(presenter);
        presenter.attachView(this);
    }

    @Override
    public void onDestroyView() {
        for (FramePresenter p : presenters) {
            p.detachView();
        }
        super.onDestroyView();
    }
}
