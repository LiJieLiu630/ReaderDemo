package com.liulije.readerdemo.book.bean;

import com.allen.library.base.BaseResponse;

import java.io.Serializable;

/**
 * @类名称: CLASS
 * @类描述: 主题书单书籍详情实体
 * @创建人：LiuL1Jie
 * @创建时间：2017/8/10 13:50
 * @备注：
 */
public class SubjectBookDetailBean extends BaseResponse implements Serializable {
    String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public SubjectBookDetailBean getBook() {
        return book;
    }

    public void setBook(SubjectBookDetailBean book) {
        this.book = book;
    }

    SubjectBookDetailBean book;

    /**
     * _id : 58c56a4f5950a2b81cd59438
     * title : 隐世十族
     * author : 原晓
     * longIntro : 世界上有十个家族拥有远高于常人的能力。他们世代隐藏在茫茫人群中。每隔一段时间，十大家族就必须聚集在一起去完成一件事情……时光流转如白驹过隙，到了科技高度发达的现代，很多古老的能力逐渐被淹没，他们的后人大多忘记了自己的使命，忘记了自己是谁。调香疗心的调香师、雕玉延寿的雕魂师、观“气” 治病的续命师、下笔成真的画幻师……时隔多年，拥有天赋异能的十大家族终于齐聚，一起向神秘的目的地出发，寻找家族之源，完成天赋使命。
     * cover : /agent/http://wfqqreader.3g.qq.com/cover/743/853743/t7_853743.jpg
     * cat : 其它
     * site : chuangshi
     * majorCate : 其它
     * minorCate :
     * banned : 0
     * latelyFollower : 96
     * wordCount : 180000
     * retentionRatio : 0
     */

    private String _id;
    private String title;
    private String author;
    private String longIntro;
    private String cover;
    private String cat;
    private String site;
    private String majorCate;
    private String minorCate;
    private int banned;
    private int latelyFollower;
    private int wordCount;
//    private int retentionRatio;

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

    public String getLongIntro() {
        return longIntro;
    }

    public void setLongIntro(String longIntro) {
        this.longIntro = longIntro;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getMajorCate() {
        return majorCate;
    }

    public void setMajorCate(String majorCate) {
        this.majorCate = majorCate;
    }

    public String getMinorCate() {
        return minorCate;
    }

    public void setMinorCate(String minorCate) {
        this.minorCate = minorCate;
    }

    public int getBanned() {
        return banned;
    }

    public void setBanned(int banned) {
        this.banned = banned;
    }

    public int getLatelyFollower() {
        return latelyFollower;
    }

    public void setLatelyFollower(int latelyFollower) {
        this.latelyFollower = latelyFollower;
    }

    public int getWordCount() {
        return wordCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

//    public int getRetentionRatio() {
//        return retentionRatio;
//    }
//
//    public void setRetentionRatio(int retentionRatio) {
//        this.retentionRatio = retentionRatio;
//    }
}
