package com.example.projectmodule.utils;

import android.util.Log;

/**
 * Created by 吴城林 on 2017/7/17.
 */

public class LogUtils {

    private static volatile String appendContent="";

    private LogUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static boolean isDebug = true;// 是否需要打印bug，可以在application的onCreate函数里面初始化
    private static final String TAG = "testing";

    // 下面四个是默认tag的函数
    public static void i(String msg) {
        if (isDebug)
            Log.i(TAG, msg + appendContent);
    }

    public static void d(String msg) {
        if (isDebug)
            Log.d(TAG, msg + appendContent);
    }

    public static void e(String msg) {
        if (isDebug)
            Log.e(TAG, msg + appendContent);
    }

    public static void v(String msg) {
        if (isDebug)
            Log.v(TAG, msg + appendContent);
    }

    // 下面是传入自定义tag的函数
    public static void i(String tag, String msg) {
        if (isDebug)
            Log.i(tag, msg + appendContent);
    }

    public static void d(String tag, String msg) {
        if (isDebug)
            Log.d(tag, msg + appendContent);
    }

    public static void e(String tag, String msg) {
        if (isDebug)
            Log.e(tag, msg + appendContent);
    }

    public static void v(String tag, String msg) {
        if (isDebug)
            Log.v(tag, msg + appendContent);
    }

    //只可以append一次，下一次append会取代上一次
    public static void append(String append) {
        appendContent = append;
    }
}
