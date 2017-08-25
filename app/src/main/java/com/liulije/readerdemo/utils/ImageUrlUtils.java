package com.liulije.readerdemo.utils;

import android.util.Log;

import java.util.ArrayList;

/**
 * @类名称: CLASS
 * @类描述:
 * @创建人：刘丽杰
 * @创建时间：2017/8/15 10:13
 * @备注：
 */
public class ImageUrlUtils {
    private static final String TAG = "ImageUrlUtils";
    // 小说图片随机图
    private static final String TRANSITION_URL_01 = "/agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F1127281%2F_1127281_685974.jpg%2F";
    private static final String TRANSITION_URL_02 = "/agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F1188355%2F_1188355_363695.jpg%2F";
    private static final String TRANSITION_URL_03 = "/agent/http://rm2.kingreader.com/book/804361/m/[640]_201509230900311453127.jpg";
    private static final String TRANSITION_URL_04 = "/agent/http://images.zhulang.com/book_cover/image/31/64/316434.jpg";
    private static final String TRANSITION_URL_05 = "/agent/http://ww3.sinaimg.cn/mw690/0067xnK7jw1ewncdj10qjj305k07swei.jpg";
    private static final String TRANSITION_URL_06 = "/agent/http://images.zhulang.com/www/image/no_book.gif";
    private static final String TRANSITION_URL_07 = "/agent/http://images.zhulang.com/book_cover/image/11/90/119044.jpg";
    private static final String TRANSITION_URL_08 = "/agent/http://images.zhulang.com/www/image/no_book.gif?1440519077";
    private static final String TRANSITION_URL_09 = "/agent/http://images.zhulang.com/www/image/no_book.gif?1464932266";
    private static final String TRANSITION_URL_10 = "/agent/http://rm2.kingreader.com/book/913262%2Fm%2F%5B640%5D_b_277756.jpg";
    private static final String TRANSITION_URL_11 = "/agent/http://wfqqreader.3g.qq.com/cover/496/145496/t5_145496.jpg";
    private static final String TRANSITION_URL_12 = "/agent/http://pic.hxcdn.net/www/images/NOVELADMINIMAGES/2008611124110.jpg";
    public static final String[] TRANSITION_URLS = new String[]{
            TRANSITION_URL_01, TRANSITION_URL_02, TRANSITION_URL_03
            , TRANSITION_URL_04, TRANSITION_URL_05, TRANSITION_URL_06
            , TRANSITION_URL_07, TRANSITION_URL_08, TRANSITION_URL_09
            , TRANSITION_URL_10, TRANSITION_URL_11, TRANSITION_URL_12
    };
    private static int current = 0;

    public static String getOneUrl() {
        Log.i(TAG, "getOneUrl: " + TRANSITION_URLS.length);
        String url = "";
        if (current < TRANSITION_URLS.length) {
            url = TRANSITION_URLS[current];
            current++;
        } else {
            current = 0;
            url = TRANSITION_URLS[current];
            current++;
        }

        return url;
    }
}
