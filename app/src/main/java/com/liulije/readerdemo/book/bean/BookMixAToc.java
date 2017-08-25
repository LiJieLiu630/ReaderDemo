package com.liulije.readerdemo.book.bean;

import com.allen.library.base.BaseResponse;

import java.io.Serializable;
import java.util.List;

/**
 * @类名称: CLASS
 * @类描述:书籍章节目录
 * @创建人：LiuL1Jie
 * @创建时间：2017/8/16 10:57
 * @备注：
 */
public class BookMixAToc extends BaseResponse implements Serializable {
    BookMixAToc mixToc;

    /**
     * _id : 53bd6513654b85b5c5eff6cb
     * book : 53bd6513521eec3229cfb84c
     * chaptersCount1 : 80
     * chaptersUpdated : 2016-07-08T13:55:33.626Z
     * updated : 2016-07-08T13:55:33.626Z
     */
    List<BookMixAToc> chapters;

    public BookMixAToc getMixToc() {
        return mixToc;
    }

    public List<BookMixAToc> getChapters() {
        return chapters;
    }

    public void setChapters(List<BookMixAToc> chapters) {
        this.chapters = chapters;
    }

    public void setMixToc(BookMixAToc mixToc) {
        this.mixToc = mixToc;
    }

    private String _id;
    private String book;
    private int chaptersCount1;
    private String chaptersUpdated;
    private String updated;
    /**
     * 章节内容链接 、章节名称
     * link : http://book.my716.com/getBooks.aspx?method=content&bookId=70027&chapterFile=U_33041_201609030709089534_5903_1.txt
     * title : 妹纸们看过来
     * unreadble : false
     */

    private String link;
    private String title;
    private boolean unreadble;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public int getChaptersCount1() {
        return chaptersCount1;
    }

    public void setChaptersCount1(int chaptersCount1) {
        this.chaptersCount1 = chaptersCount1;
    }

    public String getChaptersUpdated() {
        return chaptersUpdated;
    }

    public void setChaptersUpdated(String chaptersUpdated) {
        this.chaptersUpdated = chaptersUpdated;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isUnreadble() {
        return unreadble;
    }

    public void setUnreadble(boolean unreadble) {
        this.unreadble = unreadble;
    }
}
