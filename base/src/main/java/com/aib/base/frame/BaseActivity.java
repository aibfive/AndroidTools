package com.aib.base.frame;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;

import com.aib.base.annotate.RequestPermissionFail;
import com.aib.base.annotate.RequestPermissionSuccess;

import java.lang.reflect.Method;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author zhangdeming
 * @date 创建时间 2016/9/26
 * @description 所有Activity的基础类，负责创建一些基础操作
 */
public abstract class BaseActivity extends AppCompatActivity {

    /**
     * 用于Log数据用的标志
     */
    protected final String TAG = getClass().getSimpleName();
    protected Fragment currentFragment;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityConfigure();
        AppManager.getInstance().add(this);
        setContentView(layoutId());
        //注释绑定控件
        unbinder = ButterKnife.bind(this);
        if (getIntent().getExtras() != null) {
            receiveDataFromPreActivity(getIntent().getExtras());
        }
        initData();
        initComponent();
        initListener();
    }


    @Override
    protected void onDestroy() {
        AppManager.getInstance().remove(this);
        if(unbinder != null){
            unbinder.unbind();
        }
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected abstract void activityConfigure();

    protected abstract int layoutId();

    protected abstract void receiveDataFromPreActivity(Bundle data);

    protected abstract void initData();

    protected abstract void initComponent();

    protected abstract void initListener();


    /**
     * 跳转到一个新的Activity，不结束当前的Activity
     *
     * @param c
     * @param bundle
     */
    public void gotoActivityNotClose(Class<?> c, Bundle bundle) {
        Intent i = new Intent(this, c);
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
        Intent i = new Intent(this, c);
        if (bundle != null) {
            i.putExtras(bundle);
        }
        this.startActivity(i);
        finish();
    }

    /**
     * 跳转到一个Activity，需要回调结果
     *
     * @param requestCode
     * @param c
     * @param bundle
     */
    public void gotoActivityForResult(int requestCode, Class<?> c, Bundle bundle) {
        Intent i = new Intent(this, c);
        if (bundle != null) {
            i.putExtras(bundle);
        }
        startActivityForResult(i, requestCode);
    }

    /**
     * 用tempFragment替代当前Fragment, 并给tempFragment增加一个tag，以便下次调用，不用新建
     *
     * @param containerId
     * @param fragment
     * @param tag
     */
    protected void replaceFragment(int containerId, Fragment fragment, String tag) {
        currentFragment = fragment;
        boolean isAdd = true;
        Fragment tempFragment = getSupportFragmentManager().findFragmentByTag(tag);
        if (tempFragment == null) {
            tempFragment = fragment;
            isAdd = false;
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(containerId, tempFragment, tag);
        if (!isAdd) {
            transaction.addToBackStack(tag);
        }
        transaction.commitAllowingStateLoss();
    }

    /**
     * 获取状态栏高度
     *
     * @return
     */
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen",
                "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
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
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
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
        ActivityCompat.requestPermissions(this, permissions, code);
    }

}
