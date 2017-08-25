package com.liulije.readerdemo.book.bean;

import com.allen.library.base.BaseResponse;

import java.util.List;

/**
 * @类名称: CLASS
 * @类描述:评论
 * @创建人：LiuL1Jie
 * @创建时间：2017/8/11 14:01
 * @备注：
 */
public class ReviewBean extends BaseResponse {
    List<ReviewBean> reviews;

    public BookAuthorBean getAuthor() {
        return author;
    }

    public void setAuthor(BookAuthorBean author) {
        this.author = author;
    }

    public List<ReviewBean> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewBean> reviews) {
        this.reviews = reviews;
    }

    public ReviewBean getHelful() {
        return helful;
    }

    public void setHelful(ReviewBean helful) {
        this.helful = helful;
    }

    /**
     * total : 239
     * ok : true
     */

    private BookAuthorBean author;
    private ReviewBean helful;

    private int total;
    private boolean ok;
    /**
     * _id : 59369b8937eda56f723f0fff
     * rating : 3
     * likeCount : 36
     * state : normal
     * updated : 2017-08-11T04:40:54.545Z
     * created : 2017-06-06T12:09:45.947Z
     * commentCount : 335
     * content : 可能你们不敢相信我一个女孩子差不多把这本书看完了，我好像是14年开始看的 ，看到九百多万字的时候还在更新就没看了，当时我认为这本书特好看，我那时好像才15岁，死追这本书天天熬夜，后来看了土豆和三少的书才感觉这本书和种马文一样，睡过不知道多少女的，反正我当时看我压根分不清他那些老婆是谁谁谁，字也太多了看的心累， 比较喜欢斗破和唐门那种类型的不会太拖，老婆也少，这本书我感觉有一半都在写主角怎么把妹怎么睡妹子怎么勾搭妹子，我是刚刚在重温斗破突然想到这本书的顺便来评论一下毕竟是第一本带我入修仙这个坑的，然而带进去我出不来了，而且大多只看这种以男生为主的小说，我这个十八岁的女孩子聊这种修仙小说能和那些二十五左右的男孩子聊的特别来，他们有些都没我看的多。😂
     * title : 有女生看嘛
     */

    private String _id;
    private int rating;
    private int likeCount;
    private String state;
    private String updated;
    private String created;
    private int commentCount;
    private String content;
    private String title;
    /**
     * yes : 395
     * no : 63
     */

    private int yes;
    private int no;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYes() {
        return yes;
    }

    public void setYes(int yes) {
        this.yes = yes;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "ReviewBean{" +
                "reviews=" + reviews +
                ", author=" + author +
                ", helful=" + helful +
                ", total=" + total +
                ", ok=" + ok +
                ", _id='" + _id + '\'' +
                ", rating=" + rating +
                ", likeCount=" + likeCount +
                ", state='" + state + '\'' +
                ", updated='" + updated + '\'' +
                ", created='" + created + '\'' +
                ", commentCount=" + commentCount +
                ", content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", yes=" + yes +
                ", no=" + no +
                '}';
    }
}
