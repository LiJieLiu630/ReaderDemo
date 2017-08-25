package com.liulije.readerdemo.bean;

import com.allen.library.base.BaseResponse;

import java.io.Serializable;

/**
 * @类名称: CLASS
 * @类描述:书架 书籍实体
 * @创建人：刘丽杰
 * @创建时间：2017/8/11 16:24
 * @备注：
 */
public class CollectionBookBean extends BaseResponse implements Serializable {
    /**
     * _id : 526e8e3e7cfc087140004df7
     * author : 太一生水
     * cover : /agent/http://image.cmfu.com/books/3347382/3347382.jpg
     * shortIntro : 十大封号武帝之一，绝世古飞扬在天荡山脉陨落，于十五年后转世重生，化为天水国公子李云霄，开启了一场与当世无数天才相争锋的逆天之旅。武道九重，十方神境，从此整个世界...
     * title : 万古至尊
     * hasCp : true
     * latelyFollower : 3053
     * retentionRatio : 42.59
     * updated : 2016-07-25T15:29:51.703Z
     * chaptersCount : 2406
     * lastChapter : 第2406章 千载风云尽付一笑（大结局）
     */

    public String _id;
    public String author;
    public String cover;
    public String shortIntro;
    public String title;
    public boolean hasCp;
    public boolean isTop = false;
    public boolean isSeleted = false;
    public boolean showCheckBox = false;
    public boolean isFromSD = false;
    public String path = "";
    public int latelyFollower;
    public double retentionRatio;
    public String updated = "";
    public int chaptersCount;
    public String lastChapter;
    public String recentReadingTime = "";
    private boolean isEmptyAddMore;

    public boolean isEmptyAddMore() {
        return isEmptyAddMore;
    }

    public void setEmptyAddMore(boolean emptyAddMore) {
        isEmptyAddMore = emptyAddMore;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CollectionBookBean) {
            CollectionBookBean bean = (CollectionBookBean) obj;
            return this._id.equals(bean._id);
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return this._id.hashCode();
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getShortIntro() {
        return shortIntro;
    }

    public void setShortIntro(String shortIntro) {
        this.shortIntro = shortIntro;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isHasCp() {
        return hasCp;
    }

    public void setHasCp(boolean hasCp) {
        this.hasCp = hasCp;
    }

    public boolean isTop() {
        return isTop;
    }

    public void setTop(boolean top) {
        isTop = top;
    }

    public boolean isSeleted() {
        return isSeleted;
    }

    public void setSeleted(boolean seleted) {
        isSeleted = seleted;
    }

    public boolean isShowCheckBox() {
        return showCheckBox;
    }

    public void setShowCheckBox(boolean showCheckBox) {
        this.showCheckBox = showCheckBox;
    }

    public boolean isFromSD() {
        return isFromSD;
    }

    public void setFromSD(boolean fromSD) {
        isFromSD = fromSD;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getLatelyFollower() {
        return latelyFollower;
    }

    public void setLatelyFollower(int latelyFollower) {
        this.latelyFollower = latelyFollower;
    }

    public double getRetentionRatio() {
        return retentionRatio;
    }

    public void setRetentionRatio(double retentionRatio) {
        this.retentionRatio = retentionRatio;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
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

    public String getRecentReadingTime() {
        return recentReadingTime;
    }

    public void setRecentReadingTime(String recentReadingTime) {
        this.recentReadingTime = recentReadingTime;
    }
}

