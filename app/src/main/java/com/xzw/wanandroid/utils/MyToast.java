package com.xzw.wanandroid.utils;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import com.blankj.utilcode.util.ToastUtils;

/**
 * Description :
 * Author : XZW
 * Date : 2018/8/11
 */
public class MyToast {

    /**
     * 安全地显示短时吐司
     *
     * @param text 文本
     */
    public static void showShort(@NonNull final CharSequence text) {
        ToastUtils.showShort(text);
    }

    /**
     * 安全地显示短时吐司
     *
     * @param resId 资源Id
     */
    public static void showShort(@StringRes final int resId) {
        ToastUtils.showShort(resId);
    }

    /**
     * 安全地显示短时吐司
     *
     * @param resId 资源Id
     * @param args  参数
     */
    public static void showShort(@StringRes final int resId, final Object... args) {
        ToastUtils.showShort(resId, args);
    }

    /**
     * 安全地显示短时吐司
     *
     * @param format 格式
     * @param args   参数
     */
    public static void showShort(final String format, final Object... args) {
        ToastUtils.showShort(format, args);
    }

    /**
     * 安全地显示长时吐司
     *
     * @param text 文本
     */
    public static void showLong(@NonNull final CharSequence text) {
        ToastUtils.showLong(text);
    }

    /**
     * 安全地显示长时吐司
     *
     * @param resId 资源Id
     */
    public static void showLong(@StringRes final int resId) {
        ToastUtils.showLong(resId);
    }

    /**
     * 安全地显示长时吐司
     *
     * @param resId 资源Id
     * @param args  参数
     */
    public static void showLong(@StringRes final int resId, final Object... args) {
        ToastUtils.showLong(resId, args);
    }

    /**
     * 安全地显示长时吐司
     *
     * @param format 格式
     * @param args   参数
     */
    public static void showLong(final String format, final Object... args) {
        ToastUtils.showLong(format, args);
    }
}
