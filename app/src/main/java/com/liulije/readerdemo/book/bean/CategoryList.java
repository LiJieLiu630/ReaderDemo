package com.liulije.readerdemo.book.bean;

import com.allen.library.base.BaseResponse;

import java.io.Serializable;
import java.util.List;

/**
 * @类名称: CLASS
 * @类描述:分类 fragment 实体
 * @创建人：刘丽杰
 * @创建时间：2017/8/15 10:00
 * @备注：
 */
public class CategoryList extends BaseResponse implements Serializable {
    /**
     * male : [{"name":"玄幻","bookCount":188244},{"name":"奇幻","bookCount":24183}]
     * ok : true
     */

    public List<MaleBean> male;
    /**
     * name : 古代言情
     * bookCount : 125103
     */

    public List<MaleBean> female;

    public static class MaleBean implements Serializable {
        public String name;
        public int bookCount;
        boolean isFistMale;
        List<String> tags;
        String isFemal;

        public String isFemal() {
            return isFemal;
        }

        public void setFemal(String femal) {
            isFemal = femal;
        }

        public boolean isFistMale() {
            return isFistMale;
        }

        public void setFistMale(boolean fistMale) {
            isFistMale = fistMale;
        }

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getBookCount() {
            return bookCount;
        }

        public void setBookCount(int bookCount) {
            this.bookCount = bookCount;
        }

        public boolean isFirstMale() {
            return isFistMale;
        }

        public void setFirstMale(boolean female) {
            isFistMale = female;
        }
    }

    public List<MaleBean> getMale() {
        return male;
    }

    public void setMale(List<MaleBean> male) {
        this.male = male;
    }

    public List<MaleBean> getFemale() {
        return female;
    }

    public void setFemale(List<MaleBean> female) {
        this.female = female;
    }
}
