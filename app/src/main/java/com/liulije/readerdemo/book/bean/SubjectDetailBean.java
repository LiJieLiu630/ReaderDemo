package com.liulije.readerdemo.book.bean;

import com.allen.library.base.BaseResponse;

import java.io.Serializable;
import java.util.List;

/**
 * @类名称: CLASS
 * @类描述:书主题 详情实体
 * @创建人：LiuL1Jie
 * @创建时间：2017/8/10 13:46
 * @备注：
 */
public class SubjectDetailBean extends BaseResponse implements Serializable {
    SubjectDetailBean bookList;

    /**
     * _id : 59853d7ef2ff405622dfec78
     * updated : 2017-08-05T03:37:34.781Z
     * title : 大爷，常来玩嘛|ω･)و ̑
     * desc : (´˘`＊)♡ 不看看吗？这里有《哑舍》，《龙族》，《半面妆》，《浮生物语》，《饕餮记》，《饕餮娘子》，《百鬼集》，《妖怪鉴定师》，《妖物》，《芥子》，《青春奇妙物语》……
     * gender : female
     * created : 2017-08-05T03:37:34.767Z
     * stickStopTime : null
     * isDraft : false
     * isDistillate : null
     * collectorCount : 561
     * shareLink : http://share.zhuishushenqi.com/booklist/59853d7ef2ff405622dfec78
     * id : 59853d7ef2ff405622dfec78
     * total : 61
     */

    private String _id;
    private String updated;
    private String title;
    private String desc;
    private String gender;
    private String created;
    private Object stickStopTime;
    private boolean isDraft;
    private Object isDistillate;
    private int collectorCount;
    private String shareLink;
    private String id;
    private int total;
    List<String> tags;

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    private BookAuthorBean author;

    public List<SubjectBookDetailBean> getBooks() {
        return books;
    }

    public void setBooks(List<SubjectBookDetailBean> books) {
        this.books = books;
    }

    public SubjectDetailBean getBookList() {
        return bookList;
    }

    public void setBookList(SubjectDetailBean bookList) {
        this.bookList = bookList;
    }

    public boolean isDraft() {
        return isDraft;
    }

    public void setDraft(boolean draft) {
        isDraft = draft;
    }

    public BookAuthorBean getAuthor() {
        return author;
    }

    public void setAuthor(BookAuthorBean author) {
        this.author = author;
    }

    List<SubjectBookDetailBean> books;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Object getStickStopTime() {
        return stickStopTime;
    }

    public void setStickStopTime(Object stickStopTime) {
        this.stickStopTime = stickStopTime;
    }

    public boolean isIsDraft() {
        return isDraft;
    }

    public void setIsDraft(boolean isDraft) {
        this.isDraft = isDraft;
    }

    public Object getIsDistillate() {
        return isDistillate;
    }

    public void setIsDistillate(Object isDistillate) {
        this.isDistillate = isDistillate;
    }

    public int getCollectorCount() {
        return collectorCount;
    }

    public void setCollectorCount(int collectorCount) {
        this.collectorCount = collectorCount;
    }

    public String getShareLink() {
        return shareLink;
    }

    public void setShareLink(String shareLink) {
        this.shareLink = shareLink;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
