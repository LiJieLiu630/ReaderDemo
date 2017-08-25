package com.liulije.readerdemo.book.model;

import android.util.Log;

import com.allen.library.http.CommonObserver;
import com.allen.library.interceptor.Transformer;
import com.liulije.readerdemo.base.MyListener;
import com.liulije.readerdemo.base.MyReaderApplication;
import com.liulije.readerdemo.book.bean.BookListBean;
import com.liulije.readerdemo.book.bean.BookListParams;

import io.reactivex.disposables.Disposable;

/**
 * @类名称: CLASS
 * @类描述:
 * @创建人：LiuL1Jie
 * @创建时间：2017/8/9 14:28
 * @备注：
 */
public class Model_BookListImpl implements Model_BookList {
    private static final String TAG = "Model_BookListImpl";

    @Override
    public void getBookList(BookListParams params, final MyListener myListener) {
        MyReaderApplication.getApiServices()
                .getBookLists(params.getDuration(), params.getSort(), params.getStart(), params.getLimit(), params.getTag(), params.getGender())
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
                    protected void onSuccess(BookListBean bookListBean) {
                        myListener.onSuccess(bookListBean);
                    }
                });
    }
}
