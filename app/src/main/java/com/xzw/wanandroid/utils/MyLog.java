package com.xzw.wanandroid.utils;

import com.blankj.utilcode.util.LogUtils;

/**
 * Description: 日志工具
 * Author: xzw
 * Date: 2018/6/22
 */
public class MyLog {

    // 所有日志
    public static final int VERBOSE = 1;

    // 日志级别
    public static final int DEBUG = 2;
    public static final int INFO = 3;
    public static final int WARN = 4;
    public static final int ERROR = 5;

    // 没有日志
    public static final int NOTHING = 6;

    public static int level = VERBOSE;

    public static void v(final Object... contents) {
        if (level <= VERBOSE) {
            LogUtils.v(contents);
        }
    }

    public static void d(final Object... contents) {
        if (level <= DEBUG) {
            LogUtils.d(contents);
        }
    }

    public static void i(final Object... contents) {
        if (level <= INFO) {
            LogUtils.i(contents);
        }
    }

    public static void w(final Object... contents) {
        if (level <= WARN) {
            LogUtils.w(contents);
        }
    }

    public static void e(final Object... contents) {
        if (level <= ERROR) {
            LogUtils.e(contents);
        }
    }
}
