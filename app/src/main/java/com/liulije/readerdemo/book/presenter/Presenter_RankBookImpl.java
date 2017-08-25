package com.liulije.readerdemo.book.presenter;

import android.util.Log;

import com.allen.library.http.CommonObserver;
import com.allen.library.interceptor.Transformer;
import com.liulije.readerdemo.base.BasePresenter;
import com.liulije.readerdemo.base.MyReaderApplication;
import com.liulije.readerdemo.book.bean.BookListBean;
import com.liulije.readerdemo.book.bean.RankingList;
import com.liulije.readerdemo.book.view.View_RankBook;
import com.liulije.readerdemo.utils.ACache;

import io.reactivex.disposables.Disposable;

/**
 * @类名称: CLASS
 * @类描述:
 * @创建人：刘丽杰
 * @创建时间：2017/8/14 16:32
 * @备注：
 */
public class Presenter_RankBookImpl extends BasePresenter<View_RankBook> {
    private static final String TAG = "Presenter_RankBookImpl";
    private View_RankBook view_rank;

    public Presenter_RankBookImpl(View_RankBook view) {
        this.view_rank = view;
    }

    private boolean ifCanGetMsg = true;

    public void getRankBook() {
        if (ifCanGetMsg) {
            ifCanGetMsg = false;
            RankingList re1 = (RankingList) ACache
                    .get(MyReaderApplication
                            .getmInstance())
                    .getAsObject("two_rank_book_list");
            if (re1 != null) {
                ifCanGetMsg = true;
                view_rank.getSuccess(re1);
            } else
                MyReaderApplication.getApiServices()
                        .getRanking()
                        .compose(Transformer.<RankingList>switchSchedulers())
                        .subscribeWith(new CommonObserver<RankingList>() {
                            @Override
                            protected void getDisposable(Disposable d) {
                                mCompositeSubscription.add(d);
                            }

                            @Override
                            protected void onError(String errorMsg) {
                                ifCanGetMsg = true;
                                Log.e(TAG, "onError: " + "获取数据失败");
                            }

                            @Override
                            protected void onSuccess(RankingList rankingList) {
                                view_rank.getSuccess(rankingList);
                                ACache.get(MyReaderApplication.getmInstance()).put("two_rank_book_list", rankingList, 10000);
                                ifCanGetMsg = true;
                            }
                        });
        }
    }
}
