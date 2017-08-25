package com.liulije.readerdemo.book.model;

import com.liulije.readerdemo.base.MyListener;

/**
 * @类名称: CLASS
 * @类描述:
 * @创建人：LiuL1Jie
 * @创建时间：2017/8/9 14:25
 * @备注：
 */
public interface Model_BookDetail {
    void getBookList(String params, MyListener myListener);

    void getReview(String params, MyListener myListener);

    void getRecommendBookList(String params, MyListener myListener);
}
