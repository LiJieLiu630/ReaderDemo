package com.liulije.readerdemo.book.presenter;

import com.allen.library.base.BaseResponse;
import com.liulije.readerdemo.base.BasePresenter;
import com.liulije.readerdemo.base.MyListener;
import com.liulije.readerdemo.book.bean.BookDetailBean;
import com.liulije.readerdemo.book.bean.BookListBean;
import com.liulije.readerdemo.book.bean.ReviewBean;
import com.liulije.readerdemo.book.model.Model_BookDetail;
import com.liulije.readerdemo.book.model.Model_BookDetailImpl;
import com.liulije.readerdemo.book.view.View_BookDetail;

/**
 * @类名称: CLASS
 * @类描述:
 * @创建人：LiuL1Jie
 * @创建时间：2017/8/9 14:53
 * @备注：
 */
public class Presenter_BookDetailImpl extends BasePresenter<View_BookDetail> implements Presenter_BookDetail {
    private Model_BookDetail m;

    public Presenter_BookDetailImpl() {
        m = new Model_BookDetailImpl();
    }

    boolean ifCanGetBookList = true;

    @Override
    public void getBookDetail(String params) {
        if (ifCanGetBookList) {
            ifCanGetBookList = false;
            m.getBookList(params, new MyListener() {
                @Override
                public void onFailure(String code) {
                    ifCanGetBookList = true;
                    mView.getFailure();
                }

                @Override
                public void onSuccess(BaseResponse baseResponse) {
                    ifCanGetBookList = true;
                    BookDetailBean re = (BookDetailBean) baseResponse;
                    mView.getSuccess(re);

                }

            });
        }
    }

    boolean canGetReview = true;

    @Override
    public void getReview(String params) {
        if (canGetReview) {
            canGetReview = false;
            m.getReview(params, new MyListener() {
                @Override
                public void onFailure(String code) {
                    canGetReview = true;
                    mView.getFailure();
                }

                @Override
                public void onSuccess(BaseResponse baseResponse) {
                    canGetReview = true;
                    ReviewBean re = (ReviewBean) baseResponse;
                    mView.getReviewSuccess(re);
                }

            });
        }
    }

    boolean canGetRecommend = true;

    @Override
    public void getRecommendBookList(String params) {
        if (canGetRecommend) {
            canGetRecommend = false;
            m.getRecommendBookList(params, new MyListener() {
                @Override
                public void onFailure(String code) {
                    canGetRecommend = true;
                    mView.getFailure();
                }

                @Override
                public void onSuccess(BaseResponse baseResponse) {
                    canGetRecommend = true;
                    BookListBean re = (BookListBean) baseResponse;
                    mView.getRecommendSuccess(re);
                }

            });
        }
    }
}
