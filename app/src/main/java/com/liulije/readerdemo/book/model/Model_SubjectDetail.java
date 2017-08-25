package com.liulije.readerdemo.book.model;

import com.liulije.readerdemo.base.MyListener;

/**
 * @类名称: CLASS
 * @类描述:
 * @创建人：刘丽杰
 * @创建时间：2017/8/9 14:25
 * @备注：
 */
public interface Model_SubjectDetail {
    void getBookList(String subjectId, MyListener myListener);
}
