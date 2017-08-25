package com.liulije.readerdemo.book.bean;

import com.allen.library.base.BaseResponse;

import java.io.Serializable;
import java.util.List;

/**
 * @类名称: CLASS
 * @类描述:  书单内书籍实体
 * @创建人：刘丽杰
 * @创建时间：2017/8/9 14:35
 * @备注：
 */
public class BookListBean extends BaseResponse implements Serializable{
    /**
     * total : 1189
     * ok : true
     */

//    private int total;
//    private boolean ok;
    List<BookListBean> bookLists;
    List<BookListBean> booklists;

    public List<BookListBean> getBooklists() {
        return booklists;
    }

    public void setBooklists(List<BookListBean> booklists) {
        this.booklists = booklists;
    }

    int total;

    public List<BookListBean> getBookLists() {
        return bookLists;
    }

    public void setBookLists(List<BookListBean> bookLists) {
        this.bookLists = bookLists;
    }

    /**
     * _id : 5981f357dbe71a3d62c6c18a
     * title : 重生、军婚，娱乐圈，肉肉文
     * author : 流年绕过我阿朵😂
     * desc : 大部分是女强男更强，双处宠虐文，我已6年书龄，非常好看
     * gender : female
     * collectorCount : 803
     * cover : /agent/http://images.xxsy.net/bimg/458961.jpg
     * bookCount : 48
     */


    private String _id;
    private String title;
    private String author;
    private String desc;
    private String gender;
    private int collectorCount;
    private String cover;
    private int bookCount;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

//    public boolean isOk() {
//        return ok;
//    }
//
//    public void setOk(boolean ok) {
//        this.ok = ok;
//    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public int getCollectorCount() {
        return collectorCount;
    }

    public void setCollectorCount(int collectorCount) {
        this.collectorCount = collectorCount;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getBookCount() {
        return bookCount;
    }

    public void setBookCount(int bookCount) {
        this.bookCount = bookCount;
    }

    @Override
    public String toString() {
        return "BookListBean{" +
                "bookLists=" + bookLists +
                ", _id='" + _id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", desc='" + desc + '\'' +
                ", gender='" + gender + '\'' +
                ", collectorCount=" + collectorCount +
                ", cover='" + cover + '\'' +
                ", bookCount=" + bookCount +
                '}';
    }
}
