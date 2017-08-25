package com.liulije.readerdemo.book.view;

import com.liulije.readerdemo.book.bean.SubjectDetailBean;

/**
 * @类名称: CLASS
 * @类描述:
 * @创建人：刘丽杰
 * @创建时间：2017/8/10 14:01
 * @备注：
 */
public interface View_SubjectDetail {
    void getDetailSuccess(SubjectDetailBean bean);

    void getFailure();
}
