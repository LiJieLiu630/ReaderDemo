package com.liulije.readerdemo.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.liulije.readerdemo.base.MyReaderApplication;
import com.liulije.readerdemo.book.bean.BookListBean;
import com.liulije.readerdemo.book.bean.ChapterRead;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @类名称: CLASS
 * @类描述:收藏的书单管理类
 * @创建人：LiuL1Jie
 * @创建时间：2017/8/10 14:56
 * @备注：
 */
public class BookCacheManager {
    private static volatile BookCacheManager cacheManager;

    public void saveChapterFile(String bookId, int chapter, ChapterRead data) {
        File file = FileUtils.getChapterFile(bookId, chapter);
        FileUtils.writeFile(file.getAbsolutePath(), StringUtils.formatContent(data.getChapter().getBody()), false);
    }

    public static BookCacheManager getInstance() {
        if (cacheManager == null) {
            synchronized (BookCacheManager.class) {
                if (cacheManager == null) {
                    cacheManager = new BookCacheManager();
                }
            }
        }
        return cacheManager;
    }

    public File getChapterFile(String bookId, int chapter) {
        File file = FileUtils.getChapterFile(bookId, chapter);
        if (file != null && file.length() > 50)
            return file;
        return null;
    }

    private String getTocListKey(String bookId) {
        return bookId + "-bookToc";
    }

    /**
     * 移除书籍目录缓存
     *
     * @param context
     * @param bookId
     */
    public void removeToCList(Context context, String bookId) {
        ACache.get(context).remove(getTocListKey(bookId));
    }

    public void addBookCollection(BookListBean bookDetailBean) {
        List<BookListBean> list = getBookCollectionList();
        if (list == null) {
            list = new ArrayList<>();
        }
        for (BookListBean subjectBookDetailBean : list) {
            Log.e("shoucang", subjectBookDetailBean.getTitle());
            if (subjectBookDetailBean != null) {
                if (TextUtils.equals(subjectBookDetailBean.get_id(), bookDetailBean.get_id())) {
                    Toast.makeText(MyReaderApplication.getContext(), "已经收藏过了", Toast.LENGTH_LONG).show();
                    return;
                }

            }
        }
        list.add(bookDetailBean);
        ACache.get(MyReaderApplication.getmInstance()).put(getCollectionKey(), (Serializable) list);
        Toast.makeText(MyReaderApplication.getContext(), "收藏成功", Toast.LENGTH_LONG).show();
    }


    public void removeCollection(String id) {
        List<BookListBean> list = (List<BookListBean>) ACache
                .get(MyReaderApplication.getContext())
                .getAsObject(getCollectionKey());
        if (list == null) {
            return;
        }
        for (BookListBean bookListBean : list) {
            if (bookListBean != null) {
                if (TextUtils.equals(bookListBean.get_id(), id)) {
                    list.remove(bookListBean);
                    ACache.get(MyReaderApplication.getContext()).put(getCollectionKey(), (Serializable) list);
                    Toast.makeText(MyReaderApplication.getContext(), "移除成功", Toast.LENGTH_LONG).show();
                    break;
                }

            }
        }
    }

    private String getCollectionKey() {
        return "my_book_lists";
    }

    /**
     * 获取收藏的书单列表
     *
     * @return
     */
    public List<BookListBean> getBookCollectionList() {
        List<BookListBean> list = (List<BookListBean>) ACache
                .get(MyReaderApplication.getmInstance())
                .getAsObject(getCollectionKey());
        return list == null ? null : list;
    }


}
