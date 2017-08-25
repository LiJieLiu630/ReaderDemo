package com.liulije.readerdemo.utils;

import android.text.TextUtils;

import com.liulije.readerdemo.base.Constant;
import com.liulije.readerdemo.base.MyReaderApplication;
import com.liulije.readerdemo.bean.CollectionBookBean;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @类名称: CLASS
 * @类描述: 书架管理类
 * @创建人：LiuL1Jie
 * @创建时间：2017/8/11 16:07
 * @备注：
 */
public class CollectionManager {
    private static volatile CollectionManager instance;

    public CollectionManager() {
    }

    public static CollectionManager getInstance() {
        if (instance == null) {
            synchronized (CollectionManager.class) {
                if (instance == null) {
                    instance = new CollectionManager();
                }
            }
        }
        return instance;
    }

    /**
     * 获取书架列表
     *
     * @return
     */
    public List<CollectionBookBean> getColletionsList() {
        List<CollectionBookBean> list = (ArrayList<CollectionBookBean>)
                ACache.get(new File(Constant.PATH_COLLECT))
                        .getAsObject("collection");
        return list == null ? null : list;
    }

    /**
     * 存储书架列表
     *
     * @param bookBeanLsit
     */
    public void putColletionList(List<CollectionBookBean> bookBeanLsit) {
        ACache.get(new File(Constant.PATH_COLLECT)).put("collection", (Serializable) bookBeanLsit);
    }

    public List<CollectionBookBean> getCollectionListBySort() {
        List<CollectionBookBean> list = getColletionsList();
        if (list == null) {
            return null;
        } else {
            if (SharedPreferencesUtil.getInstance().getBoolean(Constant.ISBYUPDATESORT, false)) {
                Collections.sort(list, new LatelyUpdateTimeComparator());
            } else {

                Collections.sort(list, new RecentReadingTimeComparator());
            }
            return list;
        }
    }

    /**
     * 移除单个书架书籍
     *
     * @param bookId
     */
    public void remoreOne(String bookId) {
        List<CollectionBookBean> list = getColletionsList();
        if (list == null) {
            return;
        }
        for (CollectionBookBean bookBean : list) {
            if (TextUtils.equals(bookBean._id, bookId)) {
                list.remove(bookBean);
                putColletionList(list);
                break;
            }
        }
    }

    /**
     * 判断书籍是否已加入到书架
     *
     * @param bookId
     * @return
     */
    public boolean isCollected(String bookId) {
        List<CollectionBookBean> list = getColletionsList();
        if (list == null || list.isEmpty()) {
            return false;
        }
        for (CollectionBookBean bookBean : list) {
            if (bookBean._id.equals(bookId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断书籍是否置顶
     *
     * @param bookId
     * @return
     */
    public boolean isTop(String bookId) {
        List<CollectionBookBean> list = getColletionsList();
        if (list == null | list.isEmpty()) {
            return false;
        }
        for (CollectionBookBean bookBean : list) {
            if (bookBean._id.equals(bookId)) {
                if (bookBean.isTop) {
                    return true;
                }
            }
        }
        return false;
    }

    public void removeSomeBook(List<CollectionBookBean> removeBookList, boolean removeCache) {
        List<CollectionBookBean> list = getColletionsList();
        if (list == null) {
            return;
        }
        if (removeCache) {
            for (CollectionBookBean bookBean : removeBookList) {
                try {
                    //移除章节文件
                    FileUtils.deleteFileOrDirectory(FileUtils.getBookDir(bookBean._id));
                    //移除目录缓存
                    BookCacheManager.getInstance().removeToCList(AppUtils.getAppContext(), bookBean._id);
                    //移除阅读进展
                    SettingManager.getInstance().removeReadProgress(bookBean._id);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        list.removeAll(removeBookList);

    }

    /**
     * 加入书架
     *
     * @param bean
     * @return
     */
    public boolean add(CollectionBookBean bean) {
        if (isCollected(bean._id)) {
            return false;
        }
        List<CollectionBookBean> list = getColletionsList();
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(bean);
        putColletionList(list);
        return true;

    }

    /**
     * 置顶收藏、取消置顶
     *
     * @param bookId
     */
    public void top(String bookId, boolean isTop) {
        List<CollectionBookBean> list = getColletionsList();
        if (list == null) {
            return;
        }
        for (CollectionBookBean bean : list) {
            if (TextUtils.equals(bean._id, bookId)) {
                bean.isTop = isTop;
                list.remove(bean);
                list.add(0, bean);
                putColletionList(list);
                break;
            }
        }
    }

    /**
     * 设置最新章节和更新时间
     *
     * @param bookId
     */
    public synchronized void setLastChapterAndLatelyUpdate(String bookId, String lastChapter, String latelyUpdate) {
        List<CollectionBookBean> list = getColletionsList();
        if (list == null) {
            return;
        }
        for (CollectionBookBean bean : list) {
            if (TextUtils.equals(bean._id, bookId)) {
                bean.lastChapter = lastChapter;
                bean.updated = latelyUpdate;
                list.remove(bean);
                list.add(bean);
                putColletionList(list);
                break;
            }
        }
    }

    /**
     * 设置最近阅读时间
     *
     * @param bookId
     */
    public void setRecentReadingTime(String bookId) {
        List<CollectionBookBean> list = getColletionsList();
        if (list == null) {
            return;
        }
        for (CollectionBookBean bean : list) {
            if (TextUtils.equals(bean._id, bookId)) {
                bean.recentReadingTime = FormatUtils.getCurrentTimeString(FormatUtils.FORMAT_DATE_TIME);
                list.remove(bean);
                list.add(bean);
                putColletionList(list);
                break;
            }
        }
    }

    public void clear() {
        try {
            FileUtils.deleteFileOrDirectory(new File(Constant.PATH_COLLECT));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 自定义比较器：按更新时间来排序，置顶优先，降序
     */
    private class LatelyUpdateTimeComparator implements Comparator {
        @Override
        public int compare(Object o1, Object o2) {
            CollectionBookBean bookBean1 = (CollectionBookBean) o1;
            CollectionBookBean bookBean2 = (CollectionBookBean) o2;
            if (bookBean1.isTop && bookBean2.isTop || !bookBean1.isTop && !bookBean2.isTop) {
                return bookBean2.updated.compareTo(bookBean1.updated);
            }
            return bookBean1.isTop ? -1 : 1;
        }
    }

    /**
     * 自定义比较器：按最近阅读时间来排序，置顶优先，降序
     */
    private class RecentReadingTimeComparator implements Comparator {
        @Override
        public int compare(Object o1, Object o2) {
            CollectionBookBean p1 = (CollectionBookBean) o1;
            CollectionBookBean p2 = (CollectionBookBean) o2;

            if (p1.isTop && p2.isTop || !p1.isTop && !p2.isTop) {
                return p2.recentReadingTime.compareTo(p1.recentReadingTime);
            } else {
                return p1.isTop ? -1 : 1;
            }
        }
    }
}
