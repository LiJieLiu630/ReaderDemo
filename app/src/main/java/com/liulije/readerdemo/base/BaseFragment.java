package com.liulije.readerdemo.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @类名称: CLASS
 * @类描述:
 * @创建人：刘丽杰
 * @创建时间：2017/8/9 9:57
 * @备注：
 */
public abstract class BaseFragment<V, T extends BasePresenter<V>> extends Fragment {
    public T presenter;
    protected static Context mContext;
    private static final String TAG = "BaseFragment";
    //fragment是否显示
    protected boolean mIsVisible = false;
    public CompositeDisposable compositeDisposable;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity().getApplicationContext();
        View loadViewLayout = init(inflater, container);
        return loadViewLayout;
    }

    /**
     * 初始activity方法
     */
    private View init(LayoutInflater inflater, ViewGroup container) {
        View loadViewLayout = loadViewLayout(inflater, container);
        findViewByID(loadViewLayout);
        compositeDisposable = new CompositeDisposable();
        setListener();
        presenter = createPresenter();
        processLogic();
        return loadViewLayout;
    }

    protected abstract void findViewByID(View loadViewLayout);

    protected abstract T createPresenter();

    protected abstract void setListener();

    /**
     * 加载页面layout
     */
    protected abstract View loadViewLayout(LayoutInflater inflater, ViewGroup container);

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.w(TAG, "onActivityCreated: ");
        super.onActivityCreated(savedInstanceState);
    }

    protected abstract void processLogic();

    /**
     * 在这里实现Fragment数据的缓加载.
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            Log.w(TAG, "setUserVisibleHint: " + isVisibleToUser);
            mIsVisible = true;
            onVisible();
        } else {
            Log.w(TAG, "setUserVisibleHint: " + isVisibleToUser);
            mIsVisible = false;
        }
    }

    /**
     * 从不见到可见调用的方法
     */
    protected abstract void onVisible();


}
