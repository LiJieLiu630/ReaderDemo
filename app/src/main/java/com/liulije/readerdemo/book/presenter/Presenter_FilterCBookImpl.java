package com.liulije.readerdemo.book.presenter;

import com.allen.library.http.CommonObserver;
import com.allen.library.interceptor.Transformer;
import com.liulije.readerdemo.base.BasePresenter;
import com.liulije.readerdemo.base.MyReaderApplication;
import com.liulije.readerdemo.book.bean.BooksByFilter;
import com.liulije.readerdemo.book.bean.CategoryList;
import com.liulije.readerdemo.book.bean.CategoryListLv2;
import com.liulije.readerdemo.book.bean.Rankings;
import com.liulije.readerdemo.book.view.View_FilterCBook;
import com.liulije.readerdemo.book.view.View_RandingList;

import io.reactivex.disposables.Disposable;

/**
 * @类名称: CLASS
 * @类描述:
 * @创建人：刘丽杰
 * @创建时间：2017/8/14 17:28
 * @备注：
 */
public class Presenter_FilterCBookImpl extends BasePresenter<View_FilterCBook> {
    private static final String TAG = "Presenter_RankingListImpl";

    private boolean ifCanGetMsg = true;

    public void getBooksByFilter(String gender, String type, String major, String minor, int start) {
        if (ifCanGetMsg) {
            ifCanGetMsg = false;
            MyReaderApplication.getApiServices()
                    .getBooksByFilter(gender, type, major, minor, start, 10)
                    .compose(Transformer.<BooksByFilter>switchSchedulers())
                    .subscribe(new CommonObserver<BooksByFilter>() {
                        @Override
                        protected void getDisposable(Disposable d) {
                            mCompositeSubscription.add(d);
                        }

                        @Override
                        protected void onError(String errorMsg) {
                            ifCanGetMsg = true;
                        }

                        @Override
                        protected void onSuccess(BooksByFilter rankingList) {
                            mView.getFilterSuccess(rankingList);
                            ifCanGetMsg = true;
                        }
                    });
        }
    }


}
