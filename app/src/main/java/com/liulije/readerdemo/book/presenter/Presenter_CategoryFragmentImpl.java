package com.liulije.readerdemo.book.presenter;

import com.allen.library.http.CommonObserver;
import com.allen.library.interceptor.Transformer;
import com.liulije.readerdemo.base.BasePresenter;
import com.liulije.readerdemo.base.MyReaderApplication;
import com.liulije.readerdemo.book.bean.CategoryList;
import com.liulije.readerdemo.book.bean.CategoryListLv2;
import com.liulije.readerdemo.book.bean.RankingList;
import com.liulije.readerdemo.book.bean.Rankings;
import com.liulije.readerdemo.book.view.View_CategoryFragment;
import com.liulije.readerdemo.book.view.View_RandingList;
import com.liulije.readerdemo.utils.ACache;

import io.reactivex.disposables.Disposable;

/**
 * @类名称: CLASS
 * @类描述:
 * @创建人：刘丽杰
 * @创建时间：2017/8/14 17:28
 * @备注：
 */
public class Presenter_CategoryFragmentImpl extends BasePresenter<View_CategoryFragment> {
    private static final String TAG = "Presenter_CategoryFragmentImpl";
    View_CategoryFragment mview;

    public Presenter_CategoryFragmentImpl(View_CategoryFragment view) {
        this.mview = view;
    }

    private boolean ifCanGetMsg = true;

    public void getCategoryList() {
        if (ifCanGetMsg) {
            ifCanGetMsg = false;
            CategoryList re1 = (CategoryList) ACache
                    .get(MyReaderApplication
                            .getmInstance())
                    .getAsObject("two_category_book_list");
            if (re1 != null) {
                ifCanGetMsg = true;
                mview.getSuccess(re1);
            } else
                MyReaderApplication.getApiServices()
                        .getCategoryList()
                        .compose(Transformer.<CategoryList>switchSchedulers())
                        .subscribe(new CommonObserver<CategoryList>() {
                            @Override
                            protected void getDisposable(Disposable d) {
                                mCompositeSubscription.add(d);
                            }

                            @Override
                            protected void onError(String errorMsg) {
                                ifCanGetMsg = true;
                            }

                            @Override
                            protected void onSuccess(CategoryList categoryList) {
                                mview.getSuccess(categoryList);
                                ACache.get(MyReaderApplication.getmInstance()).put("two_category_book_list", categoryList, 10000);
                                ifCanGetMsg = true;
                            }
                        });
        }
    }


    private boolean ifCanGetType = true;

    public void getCategoryListLv2() {
        if (ifCanGetType) {
            ifCanGetType = false;
            CategoryListLv2 re1 = (CategoryListLv2) ACache
                    .get(MyReaderApplication
                            .getmInstance())
                    .getAsObject("two_category_book_list_lv2");
            if (re1 != null) {
                ifCanGetMsg = true;
                mview.getL2Success(re1);
            } else
                MyReaderApplication.getApiServices()
                        .getCategoryListLv2()
                        .compose(Transformer.<CategoryListLv2>switchSchedulers())
                        .subscribe(new CommonObserver<CategoryListLv2>() {
                            @Override
                            protected void getDisposable(Disposable d) {
                                mCompositeSubscription.add(d);
                            }

                            @Override
                            protected void onError(String errorMsg) {
                                ifCanGetType = true;
                            }

                            @Override
                            protected void onSuccess(CategoryListLv2 rankingList) {
                                mview.getL2Success(rankingList);
                                ACache.get(MyReaderApplication.getmInstance()).put("two_category_book_list_lv2", rankingList, 10000);

                                ifCanGetType = true;
                            }
                        });
        }
    }
}
