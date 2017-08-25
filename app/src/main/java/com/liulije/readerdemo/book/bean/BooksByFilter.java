package com.liulije.readerdemo.book.bean;

import com.allen.library.base.BaseResponse;

import java.util.List;

/**
 * @类名称: CLASS
 * @类描述:
 * @创建人：LiuL1Jie
 * @创建时间：2017/8/15 10:09
 * @备注：
 */
public class BooksByFilter extends BaseResponse {
    /**
     * _id : 555abb2d91d0eb814e5db04f
     * title : 全职法师
     * author : 乱
     * shortIntro : 一觉醒来，世界大变。 熟悉的高中传授的是魔法，告诉大家要成为一名出色的魔法师。 居住的都市之外游荡着袭击人类的魔物妖兽，虎视眈眈。
     * 崇尚科学的世界变成了崇尚魔法...
     * cover : /agent/http://image.cmfu.com/books/3489766/3489766.jpg
     * site : zhuishuvip
     * majorCate : 玄幻
     * latelyFollower : 109257
     * latelyFollowerBase : 0
     * minRetentionRatio : 0
     * retentionRatio : 72.88
     * lastChapter : 第1173章 文泰之死
     * tags : ["腹黑","玄幻","异界大陆"]
     */
    int total;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<BookDetailBean> books;

    public List<BookDetailBean> getBooks() {
        return books;
    }

    public void setBooks(List<BookDetailBean> books) {
        this.books = books;
    }
}
