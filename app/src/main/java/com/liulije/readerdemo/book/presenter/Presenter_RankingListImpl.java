package com.liulije.readerdemo.book.presenter;

import com.allen.library.http.CommonObserver;
import com.allen.library.interceptor.Transformer;
import com.liulije.readerdemo.base.BasePresenter;
import com.liulije.readerdemo.base.MyReaderApplication;
import com.liulije.readerdemo.book.bean.Rankings;
import com.liulije.readerdemo.book.view.View_RandingList;

import io.reactivex.disposables.Disposable;

/**
 * @类名称: CLASS
 * @类描述:
 * @创建人：LiuL1Jie
 * @创建时间：2017/8/14 17:28
 * @备注：
 */
public class Presenter_RankingListImpl extends BasePresenter<View_RandingList> {
    private static final String TAG = "Presenter_RankingListImpl";

    private boolean ifCanGetMsg = true;

    public void getRankBook(String rankingId) {
        if (ifCanGetMsg) {
            ifCanGetMsg = false;
            MyReaderApplication.getApiServices()
                    .getRanking(rankingId)
                    .compose(Transformer.<Rankings>switchSchedulers())
                    .subscribe(new CommonObserver<Rankings>() {
                        @Override
                        protected void getDisposable(Disposable d) {
                            mCompositeSubscription.add(d);
                        }

                        @Override
                        protected void onError(String errorMsg) {
                            ifCanGetMsg = true;
                        }

                        @Override
                        protected void onSuccess(Rankings rankingList) {
                            mView.Success(rankingList);
                            ifCanGetMsg = true;
                        }
                    });
        }
    }
}
