package com.liulije.readerdemo.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.liulije.readerdemo.R;
import com.liulije.readerdemo.utils.ActivitysManager;
import com.liulije.readerdemo.utils.RxBus2;
import com.liulije.readerdemo.utils.SharedPreferencesUtil;
import com.liulije.readerdemo.utils.StatusBarCompat;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @类名称: CLASS
 * @类描述:
 * @创建人：刘丽杰
 * @创建时间：2017/8/9 9:44
 * @备注：
 */
public abstract class BaseActivity<V, T extends BasePresenter<V>> extends AppCompatActivity implements View.OnClickListener {
    public T presenter;
    protected Context mContext;
    protected View view;
    //    public CompositeDisposable compositeDisposable;
    protected View statusBarView = null;
    protected int statusBarColor = 0;
    private boolean mNowMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        ActivitysManager.Instance().add(this);
        presenter = initPresenter();
        if (presenter != null)
            presenter.addView((V) this);
        initView(savedInstanceState);
        if (statusBarColor == 0) {
            statusBarView = StatusBarCompat.compat(this, ContextCompat.getColor(this, R.color.colorPrimaryDark));
        } else if (statusBarColor != -1) {
            statusBarView = StatusBarCompat.compat(this, statusBarColor);
        }

    }

    protected abstract T initPresenter();

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    /**
     * 初始activity方法
     */
    private void initView(Bundle savedInstanceState) {
        loadViewLayout(savedInstanceState);
//        compositeDisposable = new CompositeDisposable();
        findViewById();
        setListener();
        processLogic();

    }

    @Override
    protected void onDestroy() {
        ActivitysManager.Instance().remove(this);
        if (presenter != null) {
            presenter.removeView();
        }
        //取消所有订阅
        RxBus2.clear();
        super.onDestroy();

    }


    /**
     * 加载页面layout  第一步
     */
    protected abstract void loadViewLayout(Bundle savedInstanceState);

    //    /**
//     * 加载页面元素  第二步
//     */
    protected abstract void findViewById();

    //
//    /**
//     * 设置各种事件的监听器   第三步
//     */
    protected abstract void setListener();

    //
//    /**
//     * 业务逻辑处理，主要与后端交互   第四步
//     */
    protected abstract void processLogic();

    public void hideStatusBar() {
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setAttributes(attrs);
        if (statusBarView != null) {
            statusBarView.setBackgroundColor(statusBarColor);
        }

    }
}
