package com.liulije.readerdemo.book.bean;

import com.allen.library.base.BaseResponse;

import java.io.Serializable;

/**
 * @类名称: CLASS
 * @类描述:  主题书单作者实体
 * @创建人：刘丽杰
 * @创建时间：2017/8/10 13:50
 * @备注：
 */
public class BookAuthorBean extends BaseResponse implements Serializable{

    /**
     * _id : 5708c761a3aa5fe74bcf007e
     * avatar : /avatar/ed/e0/ede0d93ead30d19dbe1ac1bb49bf4d3c
     * nickname : 白泽
     * type : normal
     * lv : 8
     */

    private String _id;
    private String avatar;
    private String nickname;
    private String type;
    private int lv;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLv() {
        return lv;
    }

    public void setLv(int lv) {
        this.lv = lv;
    }
}
