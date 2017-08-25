package com.liulije.readerdemo.book.model;

import android.util.Log;

import com.allen.library.http.CommonObserver;
import com.allen.library.interceptor.Transformer;
import com.liulije.readerdemo.base.MyListener;
import com.liulije.readerdemo.base.MyReaderApplication;
import com.liulije.readerdemo.book.bean.BookDetailBean;
import com.liulije.readerdemo.book.bean.BookListBean;
import com.liulije.readerdemo.book.bean.ReviewBean;

import io.reactivex.disposables.Disposable;

/**
 * @类名称: CLASS
 * @类描述:
 * @创建人：LiuL1Jie
 * @创建时间：2017/8/9 14:28
 * @备注：
 */
public class Model_BookDetailImpl implements Model_BookDetail {
    private static final String TAG = "Model_BookListImpl";

    @Override
    public void getBookList(String params, final MyListener myListener) {
        MyReaderApplication.getApiServices()
                .getBookDetail(params)
                .compose(Transformer.<BookDetailBean>switchSchedulers())
                .subscribe(new CommonObserver<BookDetailBean>() {
                    @Override
                    protected void getDisposable(Disposable d) {
                        //方法暴露出来使用者根据需求去取消订阅
                    }

                    @Override
                    protected void onError(String errorMsg) {
                        Log.w(TAG, "onError: " + errorMsg.toString());
                        myListener.onFailure("服务器异常请稍后重试");
                        //错误处理
                    }


                    @Override
                    protected void onSuccess(BookDetailBean bookDetailBean) {
                        myListener.onSuccess(bookDetailBean);
                    }
                });
    }

    @Override
    public void getReview(String params, final MyListener myListener) {
        MyReaderApplication.getApiServices()
                .getHotReview(params)
                .compose(Transformer.<ReviewBean>switchSchedulers())
                .subscribe(new CommonObserver<ReviewBean>() {
                    @Override
                    protected void getDisposable(Disposable d) {
                        //方法暴露出来使用者根据需求去取消订阅
                    }

                    @Override
                    protected void onError(String errorMsg) {
                        Log.w(TAG, "onError: " + errorMsg.toString());
                        myListener.onFailure("服务器异常请稍后重试");
                        //错误处理
                    }


                    @Override
                    protected void onSuccess(ReviewBean bookDetailBean) {
                        myListener.onSuccess(bookDetailBean);
                    }
                });
    }

    @Override
    public void getRecommendBookList(String params, final MyListener myListener) {
        MyReaderApplication.getApiServices()
                .getRecommendBookList(params, "3")
                .compose(Transformer.<BookListBean>switchSchedulers())
                .subscribe(new CommonObserver<BookListBean>() {
                    @Override
                    protected void getDisposable(Disposable d) {
                        //方法暴露出来使用者根据需求去取消订阅
                    }

                    @Override
                    protected void onError(String errorMsg) {
                        Log.w(TAG, "onError: " + errorMsg.toString());
                        myListener.onFailure("服务器异常请稍后重试");
                        //错误处理
                    }


                    @Override
                    protected void onSuccess(BookListBean bookDetailBean) {
                        myListener.onSuccess(bookDetailBean);

                    }
                });
    }
}
