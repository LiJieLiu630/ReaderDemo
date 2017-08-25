package com.liulije.readerdemo.book.bean;

import com.allen.library.base.BaseResponse;

import java.io.Serializable;
import java.util.List;

/**
 * @类名称: CLASS
 * @类描述:
 * @创建人：刘丽杰
 * @创建时间：2017/8/14 16:20
 * @备注：
 */
public class RankingList extends BaseResponse implements Serializable{
    /**
     * female : [{"_id":"54d43437d47d13ff21cad58b","title":"追书最热榜 Top100",
     * "cover":"/ranking-cover/142319314350435","collapse":false,
     * "monthRank":"564d853484665f97662d0810","totalRank":"564d85b6dd2bd1ec660ea8e2"}]
     * ok : true
     */

    public List<MaleBean> female;
    /**
     * _id : 54d42d92321052167dfb75e3
     * title : 追书最热榜 Top100
     * cover : /ranking-cover/142319144267827
     * collapse : false
     * monthRank : 564d820bc319238a644fb408
     * totalRank : 564d8494fe996c25652644d2
     */

    public List<MaleBean> male;

    public List<MaleBean> getFemale() {
        return female;
    }

    public void setFemale(List<MaleBean> female) {
        this.female = female;
    }

    public List<MaleBean> getMale() {
        return male;
    }

    public void setMale(List<MaleBean> male) {
        this.male = male;
    }

    public static class MaleBean implements Serializable{
        public String _id;
        public String title;
        public String cover;
        public boolean collapse;
        public String monthRank;
        public String totalRank;

        public MaleBean() {
        }

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

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public boolean isCollapse() {
            return collapse;
        }

        public void setCollapse(boolean collapse) {
            this.collapse = collapse;
        }

        public String getMonthRank() {
            return monthRank;
        }

        public void setMonthRank(String monthRank) {
            this.monthRank = monthRank;
        }

        public String getTotalRank() {
            return totalRank;
        }

        public void setTotalRank(String totalRank) {
            this.totalRank = totalRank;
        }

        public MaleBean(String title) {
            this.title = title;
        }
    }
}
