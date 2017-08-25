package com.liulije.readerdemo.book.view;

import com.liulije.readerdemo.book.bean.BookMixAToc;
import com.liulije.readerdemo.book.bean.ChapterRead;

/**
 * @类名称: CLASS
 * @类描述:
 * @创建人：刘丽杰
 * @创建时间：2017/8/16 11:06
 * @备注：
 */
public interface View_ReadBook {
    void getChapterSuccess(BookMixAToc bookMixAToc);

    void getContentSuccess(ChapterRead chapterRead,int chapter);
}
