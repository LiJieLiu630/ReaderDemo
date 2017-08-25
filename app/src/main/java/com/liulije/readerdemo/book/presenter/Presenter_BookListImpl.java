package com.liulije.readerdemo.book.presenter;

import android.util.Log;

import com.allen.library.base.BaseResponse;
import com.liulije.readerdemo.base.BasePresenter;
import com.liulije.readerdemo.base.MyListener;
import com.liulije.readerdemo.base.MyReaderApplication;
import com.liulije.readerdemo.book.bean.BookListBean;
import com.liulije.readerdemo.book.bean.BookListParams;
import com.liulije.readerdemo.book.model.Model_BookListImpl;
import com.liulije.readerdemo.book.view.View_BookList;
import com.liulije.readerdemo.utils.ACache;

/**
 * @类名称: CLASS
 * @类描述:
 * @创建人：刘丽杰
 * @创建时间：2017/8/9 14:53
 * @备注：
 */
public class Presenter_BookListImpl extends BasePresenter<View_BookList> implements Presenter_BookList {
    private View_BookList mView;
    private Model_BookListImpl m;

    public Presenter_BookListImpl(View_BookList mView) {
        this.mView = mView;
        m = new Model_BookListImpl();
    }

    boolean ifCanGetBookList = true;
    private static final String TAG = "Presenter_BookListImpl";

    @Override
    public void getBookList(BookListParams params) {
        if (ifCanGetBookList) {
            ifCanGetBookList = false;
            BookListBean re1 = (BookListBean) ACache
                    .get(MyReaderApplication
                            .getmInstance())
                    .getAsObject("two_subject_book_list");
            if (re1 != null && re1.getTotal() != 0) {
                Log.i(TAG, "getBookList: 来自存储");
                ifCanGetBookList = true;
                mView.getBookList(re1);
            } else {
                m.getBookList(params, new MyListener() {
                    @Override
                    public void onFailure(String code) {
                        ifCanGetBookList = true;
                        mView.getBookListFailure();
                    }

                    @Override
                    public void onSuccess(BaseResponse baseResponse) {
                        ifCanGetBookList = true;
                        BookListBean re = (BookListBean) baseResponse;
                        ACache.get(MyReaderApplication.getmInstance()).put("two_subject_book_list", re, 10000);
                        mView.getBookList(re);
//                    Log.w("ddd", "onSuccess: " + re.toString());
                    }

                });
            }

        }
    }
}
