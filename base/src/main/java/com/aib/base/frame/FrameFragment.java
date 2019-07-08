package com.aib.base.frame;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import org.greenrobot.eventbus.EventBus;

import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author zhangdeming
 * @date 创建时间 2016/12/7
 * @description 描述类的功能
 */

public abstract class FrameFragment extends Fragment {

    protected FrameActivity context;
    protected View rootView;
    protected boolean isInit = false;
    private boolean isRegister = false;
    private Unbinder unbinder;

    protected abstract int layoutId();

    protected abstract void initData();

    protected abstract void initComponent();

    protected abstract void initListener();

    protected abstract void refreshUI();

    public void registerEventBus() {
        isRegister = true;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        if (!isInit) {
            initComponent();
            initListener();
            isInit = true;
        }
        if (isRegister) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        refreshUI();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = setContentView(inflater, layoutId());
        return view;
    }

    protected FrameActivity getFrameActivity() {
        return (FrameActivity) getActivity();
    }

    /**
     * 调用该办法可避免重复加载UI
     */
    public View setContentView(LayoutInflater inflater, int resId) {
        if (rootView == null) {
            rootView = inflater.inflate(resId, null);
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (isRegister) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(unbinder != null) {
            unbinder.unbind();
        }
    }

    /**
     * 跳转到一个新的Activity，不结束当前的Activity
     *
     * @param c
     * @param bundle
     */
    public void gotoActivityNotClose(Class<?> c, Bundle bundle) {
        Intent i = new Intent(this.getActivity(), c);
        if (bundle != null) {
            i.putExtras(bundle);
        }
        this.startActivity(i);
    }

    /**
     * 跳转到一个新的Activity，并将当前的Activity结束
     *
     * @param c
     * @param bundle
     */
    public void gotoActivity(Class<?> c, Bundle bundle) {
        Intent i = new Intent(this.getActivity(), c);
        if (bundle != null) {
            i.putExtras(bundle);
        }
        this.startActivity(i);
        this.getActivity().finish();
    }

    /**
     * 跳转到一个Activity，需要回调结果
     *
     * @param requestCode
     * @param c
     * @param bundle
     */
    public void gotoActivityForResult(int requestCode, Class<?> c, Bundle bundle) {
        Intent i = new Intent(this.getActivity(), c);
        if (bundle != null) {
            i.putExtras(bundle);
        }
        startActivityForResult(i, requestCode);
    }
}
