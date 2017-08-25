package com.liulije.readerdemo.book.bean;

import java.io.Serializable;

/**
 * @类名称: CLASS
 * @类描述:
 * @创建人：刘丽杰
 * @创建时间：2017/8/9 14:48
 * @备注：
 */
public class BookListParams implements Serializable{
    /**
     * (@Query("duration") String duration,
     *
     * @Query("sort") String sort,
     * @Query("start") String start,
     * @Query("limit") String limit,
     * @Query("tag") String tag,
     * @Query("gender") String gender);
     */

    String duration;
    String sort;
    String start;
    String limit;
    String tag;
    String gender;

    @Override
    public String toString() {
        return "BookListParams{" +
                "duration='" + duration + '\'' +
                ", sort='" + sort + '\'' +
                ", start='" + start + '\'' +
                ", limit='" + limit + '\'' +
                ", tag='" + tag + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
