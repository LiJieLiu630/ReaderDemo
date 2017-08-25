package com.liulije.readerdemo.book.view;

import com.liulije.readerdemo.book.bean.BookDetailBean;
import com.liulije.readerdemo.book.bean.BookListBean;
import com.liulije.readerdemo.book.bean.ReviewBean;

/**
 * @类名称: CLASS
 * @类描述:
 * @创建人：刘丽杰
 * @创建时间：2017/8/11 9:51
 * @备注：
 */
public interface View_BookDetail {
    void getSuccess(BookDetailBean bean);

    void getFailure();


    void getReviewSuccess(ReviewBean bean);

    void getRecommendSuccess(BookListBean bean);
}
