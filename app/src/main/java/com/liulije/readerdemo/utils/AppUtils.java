package com.liulije.readerdemo.utils;

import android.content.Context;

/**
 * @类名称: CLASS
 * @类描述:
 * @创建人：LiuL1Jie
 * @创建时间：2017/8/10 15:08
 * @备注：
 */
public class AppUtils {
    private static Context mContext;
    private static Thread mUiThread;

    public static void init(Context context) {
        mContext = context;
        mUiThread = Thread.currentThread();
    }

    public static Context getAppContext() {
        return mContext;
    }
}
