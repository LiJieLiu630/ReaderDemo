package com.liulije.readerdemo.book.view;

import com.liulije.readerdemo.book.bean.BookListBean;

/**
 * @类名称: CLASS
 * @类描述:
 * @创建人：刘丽杰
 * @创建时间：2017/8/9 14:53
 * @备注：
 */
public interface View_BookList {
    void getBookList(BookListBean mlist);

    void getBookListFailure();
}
