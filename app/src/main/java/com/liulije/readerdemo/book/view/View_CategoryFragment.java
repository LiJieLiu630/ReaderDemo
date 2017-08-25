package com.liulije.readerdemo.book.view;

import com.liulije.readerdemo.book.bean.CategoryList;
import com.liulije.readerdemo.book.bean.CategoryListLv2;

/**
 * @类名称: CLASS
 * @类描述:
 * @创建人：LiuL1Jie
 * @创建时间：2017/8/15 10:58
 * @备注：
 */
public interface View_CategoryFragment {
    void getSuccess(CategoryList list);

    void getL2Success(CategoryListLv2 categoryListLv2);
}
