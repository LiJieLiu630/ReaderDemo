package com.liulije.readerdemo.api;

import com.liulije.readerdemo.book.bean.BookMixAToc;
import com.liulije.readerdemo.book.bean.BooksByFilter;
import com.liulije.readerdemo.book.bean.CategoryList;
import com.liulije.readerdemo.book.bean.CategoryListLv2;
import com.liulije.readerdemo.book.bean.ChapterRead;
import com.liulije.readerdemo.book.bean.RankingList;
import com.liulije.readerdemo.book.bean.BookDetailBean;
import com.liulije.readerdemo.book.bean.BookListBean;
import com.liulije.readerdemo.book.bean.Rankings;
import com.liulije.readerdemo.book.bean.ReviewBean;
import com.liulije.readerdemo.book.bean.SubjectDetailBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @类名称: CLASS
 * @类描述:
 * @创建人：LiuL1Jie
 * @创建时间：2017/8/9 14:30
 * @备注：
 */
public interface ApiBookService {

    /**
     * 获取主题书单列表
     * 本周最热：duration=last-seven-days&sort=collectorCount
     * 最新发布：duration=all&sort=created
     * 最多收藏：duration=all&sort=collectorCount
     *
     * @param tag    都市、古代、架空、重生、玄幻、网游
     * @param gender male、female
     * @param limit  20
     * @return
     */
    //获取主题书单
    @GET("/book-list")
    Observable<BookListBean> getBookLists(@Query("duration") String duration, @Query("sort") String sort
            , @Query("start") String start, @Query("limit") String limit, @Query("tag") String tag, @Query("gender") String gender);


    /**
     * 获取主题书单详情
     *
     * @param bookListId
     * @return
     */
    @GET("/book-list/{bookListId}")
    Observable<SubjectDetailBean> getBookListDetail(@Path("bookListId") String bookListId);


    /**
     * 获取书籍信息
     *
     * @param bookId
     * @return
     */

    @GET("/book/{bookId}")
    Observable<BookDetailBean> getBookDetail(@Path("bookId") String bookId);

    /**
     * 获取评论
     *
     * @param book
     * @return
     */
    @GET("/post/review/best-by-book")
    Observable<ReviewBean> getHotReview(@Query("book") String book);

    /**
     * 主题推荐
     *
     * @param bookId
     * @param limit
     * @return
     */
    @GET("/book-list/{bookId}/recommend")
    Observable<BookListBean> getRecommendBookList(@Path("bookId") String bookId, @Query("limit") String limit);

    /**
     * 获取所有排行榜
     *
     * @return
     */
    @GET("/ranking/gender")
    Observable<RankingList> getRanking();

    /**
     * 获取单一排行榜
     * 周榜：rankingId->_id
     * 月榜：rankingId->monthRank
     * 总榜：rankingId->totalRank
     *
     * @return
     */
    @GET("/ranking/{rankingId}")
    Observable<Rankings> getRanking(@Path("rankingId") String rankingId);


    /**
     * 获取分类
     *
     * @return
     */
    @GET("/cats/lv2/statistics")
    Observable<CategoryList> getCategoryList();

    /**
     * 获取二级分类
     *
     * @return
     */
    @GET("/cats/lv2")
    Observable<CategoryListLv2> getCategoryListLv2();

    /**
     * 按分类获取书籍列表
     *
     * @param gender male、female
     * @param type   hot(热门)、new(新书)、reputation(好评)、over(完结)
     * @param major  玄幻
     * @param minor  东方玄幻、异界大陆、异界争霸、远古神话
     * @param limit  50
     * @return
     */
    @GET("/book/by-categories")
    Observable<BooksByFilter> getBooksByFilter(@Query("gender") String gender, @Query("type") String type, @Query("major") String major, @Query("minor") String minor, @Query("start") int start, @Query("limit") int limit);

    /**
     * 获取书籍内容
     *
     * @param url
     * @return
     */
    @GET("http://chapter2.zhuishushenqi.com/chapter/{url}")
    Observable<ChapterRead> getChapterRead(@Path("url") String url);

    /**
     * 获取书籍章节目录
     *
     * @param bookId
     * @param view
     * @return
     */
    @GET("/mix-atoc/{bookId}")
    Observable<BookMixAToc> getBookMixAToc(@Path("bookId") String bookId, @Query("view") String view);
}
