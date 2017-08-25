package com.liulije.readerdemo.book.view;

import com.liulije.readerdemo.book.bean.Rankings;

/**
 * @类名称: CLASS
 * @类描述:单一排行榜数据
 * @创建人：刘丽杰
 * @创建时间：2017/8/14 17:27
 * @备注：
 */
public interface View_RandingList {
    void Success(Rankings rankings);

    void failure();


}
