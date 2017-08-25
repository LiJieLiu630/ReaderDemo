package com.liulije.readerdemo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @类名称: CLASS
 * @类描述:
 * @创建人：刘丽杰
 * @创建时间：2017/8/10 14:53
 * @备注：
 */
public class FormatUtils {
    public static String formatWordCount(int wordCount) {
        if (wordCount / 10000 > 0) {
            return (int) ((wordCount / 10000f) + 0.5) + "万字";
        } else if (wordCount / 1000 > 0) {
            return (int) ((wordCount / 1000f) + 0.5) + "千字";
        } else {
            return wordCount + "字";
        }
    }

    private static SimpleDateFormat sdf = new SimpleDateFormat();
    public final static String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     * 获取当前日期的指定格式的字符串
     *
     * @param format 指定的日期时间格式，若为null或""则使用指定的格式"yyyy-MM-dd HH:mm:ss.SSS"
     * @return
     */
    public static String getCurrentTimeString(String format) {
        if (format == null || format.trim().equals("")) {
            sdf.applyPattern(FORMAT_DATE_TIME);
        } else {
            sdf.applyPattern(format);
        }
        return sdf.format(new Date());
    }
}
