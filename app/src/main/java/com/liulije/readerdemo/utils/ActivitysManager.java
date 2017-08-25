package com.liulije.readerdemo.utils;

import android.app.Activity;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/**
 * @类名称: CLASS
 * @类描述:
 * @创建人：刘丽杰
 * @创建时间：2017/8/9 13:39
 * @备注：
 */
public class ActivitysManager {
    //tag组
    public static final String OTHER_TAG = "other";
    public static final String LOGIN_TAG = "login";

    private static final ActivitysManager instance = new ActivitysManager();
    private static final HashMap<String, ArrayList<Activity>> activities = new HashMap<>();
    private static final LinkedList<Activity> linkActivities = new LinkedList<>();
    private int size;

    private ActivitysManager() {
    }

    public static ActivitysManager Instance() {
        return instance;
    }


    /**
     * 清除指定tag的activity组
     *
     * @param tags
     */
    public void clear(Activity filterActivity, String... tags) {
        if (null != tags) {
            for (int i = 0; i < tags.length; i++) {
                ArrayList<Activity> activities = ActivitysManager.activities.get(tags[i]);
                if (null != activities && !activities.isEmpty()) {
                    Iterator<Activity> iterator = activities.iterator();
                    while (iterator.hasNext()) {
                        Activity activity = iterator.next();
                        if (filterActivity != activity) {
                            linkActivities.remove(activity);
                            iterator.remove();//注意这个地方
                            activity.finish();
                            size--;
                        }
                    }
                }
            }
        }
    }

    /**
     * 清除指定tag的activity组
     *
     * @param tags
     */
    public void clear(String... tags) {
        if (null != tags) {
            for (int i = 0; i < tags.length; i++) {
                ArrayList<Activity> activities = ActivitysManager.activities.get(tags[i]);
                if (null != activities && !activities.isEmpty()) {
                    Iterator<Activity> iterator = activities.iterator();
                    while (iterator.hasNext()) {
                        Activity activity = iterator.next();
                        if (null != activity) {
                            linkActivities.remove(activity);
                            iterator.remove();
                            activity.finish();
                            size--;
                        }
                    }
                }
            }
        }
    }

    /**
     * 清空所有activity
     */
    public void clearAll() {
        clearAll(null);
    }

    /**
     * 清空除指定过滤activity外的所有activity对象
     *
     * @param filterActivity
     */
    public void clearAll(Activity filterActivity) {
        if (!activities.isEmpty()) {
            for (Map.Entry<String, ArrayList<Activity>> entry : activities.entrySet()) {
                ArrayList<Activity> activities = entry.getValue();
                if (null != activities && !activities.isEmpty()) {
                    Iterator<Activity> iterator = activities.iterator();
                    while (iterator.hasNext()) {
                        Activity activity = iterator.next();
                        if (filterActivity != activity) {
                            linkActivities.remove(activity);
                            iterator.remove();//注意这个地方
                            activity.finish();
                            size--;
                        }
                    }
                }
            }
        }
    }

    /**
     * 添加activity
     *
     * @param activity
     */
    public void add(Activity activity) {
        add(null, activity);
    }

    /**
     * 添加指定activity到tag组
     *
     * @param tag
     * @param activity
     */
    public void add(String tag, Activity activity) {
        tag = !TextUtils.isEmpty(tag) ? OTHER_TAG : tag;
        ArrayList<Activity> activities = ActivitysManager.activities.get(tag);
        if (null == activities) {
            activities = new ArrayList<>();
            ActivitysManager.activities.put(tag, activities);
        }
        linkActivities.addFirst(activity);
        activities.add(activity);
        size++;
    }


    /**
     * 移除指定activity
     *
     * @param removeActivity
     */
    public void remove(Activity removeActivity) {
        if (!activities.isEmpty()) {
            for (Map.Entry<String, ArrayList<Activity>> entry : activities.entrySet()) {
                ArrayList<Activity> activities = entry.getValue();
                if (null != activities && !activities.isEmpty()) {
                    Iterator<Activity> iterator = activities.iterator();
                    while (iterator.hasNext()) {
                        Activity activity = iterator.next();
                        if (activity == removeActivity) {
                            linkActivities.remove(removeActivity);
                            iterator.remove();//注意这个地方
                            activity.finish();
                            size--;
                        }
                    }
                }
            }
        }
    }

    /**
     * 获得栈顶的activity对象
     *
     * @return
     */
    public Activity getTopActivity() {
        return linkActivities.getFirst();
    }

    public int size() {
        return size;
    }
}
