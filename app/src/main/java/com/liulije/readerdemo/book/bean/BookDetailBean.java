package com.liulije.readerdemo.book.bean;

import com.allen.library.base.BaseResponse;

import java.util.List;

/**
 * @类名称: CLASS
 * @类描述: 书籍详情实体
 * @创建人：LiuL1Jie
 * @创建时间：2017/8/10 17:48
 * @备注：
 */
public class BookDetailBean extends BaseResponse {

    /**
     * _id : 574efd78ee90fc851718361d
     * title : 竹马爱青梅
     * author : 陌言川
     * longIntro : 东妍十五岁那年早恋不到半年就把人家给甩了，只是高俞楷从此却是阴魂不散，明明见了她掉头就走的人，却对她的事情了如指掌。高俞楷这一生做过最浪漫的事情就是这一辈子只追了吴东妍这一个姑娘，终其一生，只为一人。
     * cover : /agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F1078117%2F_1078117_769810.jpg%2F
     * majorCate : 青春校园
     * minorCate : 青春校园
     * sizetype : -1
     * superscript :
     * currency : 0
     * contentType : txt
     * _le : false
     * allowMonthly : false
     * allowVoucher : true
     * allowBeanVoucher : false
     * hasCp : true
     * postCount : 9
     * latelyFollower : 1620
     * followerCount : 0
     * wordCount : 160000
     * serializeWordCount : -1
     * retentionRatio : 17.21
     * updated : 2016-06-01T15:21:28.646Z
     * isSerial : false
     * chaptersCount : 49
     * lastChapter : 第49章
     * gender : ["female"]
     * tags : ["青春校园","浪漫青春","都市"]
     * cat : 青春校园
     * donate : false
     * _gg : false
     * discount : null
     */

    private String _id;
    private String title;
    private String author;
    private String longIntro;

    public boolean isSerial() {
        return isSerial;
    }

    public void setSerial(boolean serial) {
        isSerial = serial;
    }

    public String getShortIntro() {

        return shortIntro;
    }

    public void setShortIntro(String shortIntro) {
        this.shortIntro = shortIntro;
    }

    private String shortIntro;
    private String cover;
    private String majorCate;
    private String minorCate;
    private int sizetype;
    private String superscript;
    private int currency;
    private String contentType;
    private boolean _le;
    private boolean allowMonthly;
    private boolean allowVoucher;
    private boolean allowBeanVoucher;
    private boolean hasCp;
    private int postCount;
    private int latelyFollower;
    private int followerCount;
    private int wordCount;
    private int serializeWordCount;
    private String retentionRatio;
    private String updated;
    private boolean isSerial;
    private int chaptersCount;
    private String lastChapter;
    private String cat;
    private boolean donate;
    private boolean _gg;
    private Object discount;
    private List<String> gender;
    private List<String> tags;

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

    public int getSizetype() {
        return sizetype;
    }

    public void setSizetype(int sizetype) {
        this.sizetype = sizetype;
    }

    public String getSuperscript() {
        return superscript;
    }

    public void setSuperscript(String superscript) {
        this.superscript = superscript;
    }

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public boolean is_le() {
        return _le;
    }

    public void set_le(boolean _le) {
        this._le = _le;
    }

    public boolean isAllowMonthly() {
        return allowMonthly;
    }

    public void setAllowMonthly(boolean allowMonthly) {
        this.allowMonthly = allowMonthly;
    }

    public boolean isAllowVoucher() {
        return allowVoucher;
    }

    public void setAllowVoucher(boolean allowVoucher) {
        this.allowVoucher = allowVoucher;
    }

    public boolean isAllowBeanVoucher() {
        return allowBeanVoucher;
    }

    public void setAllowBeanVoucher(boolean allowBeanVoucher) {
        this.allowBeanVoucher = allowBeanVoucher;
    }

    public boolean isHasCp() {
        return hasCp;
    }

    public void setHasCp(boolean hasCp) {
        this.hasCp = hasCp;
    }

    public int getPostCount() {
        return postCount;
    }

    public void setPostCount(int postCount) {
        this.postCount = postCount;
    }

    public int getLatelyFollower() {
        return latelyFollower;
    }

    public void setLatelyFollower(int latelyFollower) {
        this.latelyFollower = latelyFollower;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(int followerCount) {
        this.followerCount = followerCount;
    }

    public int getWordCount() {
        return wordCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    public int getSerializeWordCount() {
        return serializeWordCount;
    }

    public void setSerializeWordCount(int serializeWordCount) {
        this.serializeWordCount = serializeWordCount;
    }

    public String getRetentionRatio() {
        return retentionRatio;
    }

    public void setRetentionRatio(String retentionRatio) {
        this.retentionRatio = retentionRatio;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public boolean isIsSerial() {
        return isSerial;
    }

    public void setIsSerial(boolean isSerial) {
        this.isSerial = isSerial;
    }

    public int getChaptersCount() {
        return chaptersCount;
    }

    public void setChaptersCount(int chaptersCount) {
        this.chaptersCount = chaptersCount;
    }

    public String getLastChapter() {
        return lastChapter;
    }

    public void setLastChapter(String lastChapter) {
        this.lastChapter = lastChapter;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public boolean isDonate() {
        return donate;
    }

    public void setDonate(boolean donate) {
        this.donate = donate;
    }

    public boolean is_gg() {
        return _gg;
    }

    public void set_gg(boolean _gg) {
        this._gg = _gg;
    }

    public Object getDiscount() {
        return discount;
    }

    public void setDiscount(Object discount) {
        this.discount = discount;
    }

    public List<String> getGender() {
        return gender;
    }

    public void setGender(List<String> gender) {
        this.gender = gender;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "BookDetailBean{" +
                "_id='" + _id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", longIntro='" + longIntro + '\'' +
                ", cover='" + cover + '\'' +
                ", majorCate='" + majorCate + '\'' +
                ", minorCate='" + minorCate + '\'' +
                ", sizetype=" + sizetype +
                ", superscript='" + superscript + '\'' +
                ", currency=" + currency +
                ", contentType='" + contentType + '\'' +
                ", _le=" + _le +
                ", allowMonthly=" + allowMonthly +
                ", allowVoucher=" + allowVoucher +
                ", allowBeanVoucher=" + allowBeanVoucher +
                ", hasCp=" + hasCp +
                ", postCount=" + postCount +
                ", latelyFollower=" + latelyFollower +
                ", followerCount=" + followerCount +
                ", wordCount=" + wordCount +
                ", serializeWordCount=" + serializeWordCount +
                ", retentionRatio='" + retentionRatio + '\'' +
                ", updated='" + updated + '\'' +
                ", isSerial=" + isSerial +
                ", chaptersCount=" + chaptersCount +
                ", lastChapter='" + lastChapter + '\'' +
                ", cat='" + cat + '\'' +
                ", donate=" + donate +
                ", _gg=" + _gg +
                ", discount=" + discount +
                ", gender=" + gender +
                ", tags=" + tags +
                '}';
    }
}
