package com.aib.base.frame;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.aib.base.annotate.RequestPermissionFail;
import com.aib.base.annotate.RequestPermissionSuccess;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Method;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
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
    private Unbinder unbinder;

    protected abstract int layoutId();

    protected void initData(){

    }

    protected void initComponent(){

    }

    protected void initListener(){

    }

    protected void refreshUI(){

    }

    public void registerEventBus() {
        EventBus.getDefault().register(this);
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
        if (EventBus.getDefault().isRegistered(this)) {
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

    /**
     * 封装获取权限的逻辑
     *
     * @param code
     * @param permissions
     */
    public void needPermission(int code, String... permissions) {
        if (hasPermission(permissions)) {
            doRequestPermissionSuccess(code);
        } else {
            requestPermission(code, permissions);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        boolean hasPermission = true;
        for (int results : grantResults) {
            if (results != PackageManager.PERMISSION_GRANTED) {
                hasPermission = false;
                break;
            }
        }
        if (hasPermission) {
            doRequestPermissionSuccess(requestCode);
        } else {
            doRequestPermissionFail(requestCode);
        }
    }

    /**
     * 执行获取权限成功方法
     *
     * @param code
     */
    protected void doRequestPermissionSuccess(int code) {
        Method[] methods = getClass().getMethods();
        for (Method method : methods) {
            RequestPermissionSuccess r = method.getAnnotation(RequestPermissionSuccess.class);
            if (r != null && r.requestCode() == code) {
                try {
                    method.invoke(this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 执行获取权限失败方法
     *
     * @param code
     */
    protected void doRequestPermissionFail(int code) {
        Method[] methods = getClass().getMethods();
        for (Method method : methods) {
            RequestPermissionFail r = method.getAnnotation(RequestPermissionFail.class);
            if (r != null && r.requestCode() == code) {
                try {
                    method.invoke(this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 检查权限是否拥有
     *
     * @param permissions
     * @return
     */
    public boolean hasPermission(String... permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(getContext(), permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }


    /**
     * 请求权限
     *
     * @param code
     * @param permissions
     */
    public void requestPermission(int code, String... permissions) {
        ActivityCompat.requestPermissions(getActivity(), permissions, code);
    }

}
