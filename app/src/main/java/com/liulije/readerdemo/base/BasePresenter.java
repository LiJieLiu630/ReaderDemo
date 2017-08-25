package com.liulije.readerdemo.base;

import org.reactivestreams.Subscription;

import io.reactivex.CompletableOnSubscribe;
import io.reactivex.disposables.CompositeDisposable;

/**
 * @类名称: CLASS
 * @类描述:
 * @创建人：LiuL1Jie
 * @创建时间：2017/8/9 13:37
 * @备注：
 */
public class BasePresenter<T> {
    private static final String TAG = "BasePresenter";
    public T mView;
    protected CompositeDisposable mCompositeSubscription = new CompositeDisposable();

    public void addView(T mView) {
        this.mView = mView;
    }

    public void removeView() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.clear();
        }
        mView = null;
    }


}
