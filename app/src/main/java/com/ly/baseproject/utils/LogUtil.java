package com.ly.baseproject.utils;

import android.util.Log;

/**
 * @author ly
 * @version V1.0
 * @Package com.ly.baseproject.base.utils
 * @Description: app日志管理类
 * @date 2016/12/1 15:56
 */
public class LogUtil {

    private static LogUtil instance = null;
    private boolean DEBUG = false; //是否为debug模式

    public LogUtil() {
    }

    public static LogUtil getInstance() {
        if (instance == null) {
            instance = new LogUtil();
        }
        return instance;
    }

    public  void setDEBUG(boolean DEBUG) {
        this.DEBUG = DEBUG;
    }

    /**
     * 输出日志信息
     *
     * @param tag 标签
     * @param msg 日志内容
     */
    public void printInfoLog(String tag, String msg) {
        if (DEBUG) {
            Log.i(tag, msg);
        }
    }

    /**
     * 输出错误日志信息
     *
     * @param tag 标签
     * @param msg 日志内容
     */
    public void printErrorLog(String tag, String msg) {
        if (DEBUG) {
            Log.e(tag, msg);
        }
    }
}
