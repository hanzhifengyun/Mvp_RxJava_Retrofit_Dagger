package com.hanzhifengyun.mvp_rxjava_retrofit_dagger.util;

import android.util.Log;

/**
 * 用于控制Log打印, 通过设置LEVEL来控制需要打印出的日志
 */
public class LogUtil {
    public static final int VERBOSE = 1;
    public static final int DEBUG = 2;
    public static final int INFO = 3;
    public static final int WARN = 4;
    public static final int ERROR = 5;
    public static final int NOTHING = 6;

    private static int sLevel = NOTHING;

    public static void setLevel(int level) {
        sLevel = level;
    }

    public static void v(String tag, String msg) {
        if (sLevel <= VERBOSE) {
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (sLevel <= DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (sLevel <= INFO) {
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (sLevel <= WARN) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (sLevel <= ERROR) {
            Log.e(tag, msg);
        }
    }

}
