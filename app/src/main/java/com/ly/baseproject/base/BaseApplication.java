package com.ly.baseproject.base;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import com.ly.baseproject.utils.LogUtil;

/**
 * @author ly
 * @version V1.0
 * @Package com.ly.baseproject.base
 * @Description: application基类
 * @date 2016/11/16 14:08
 */
public class BaseApplication  extends Application {

    static Context _context; //全局上下文对象
    static Resources _resource; //全局资源文件对象

    @Override
    public void onCreate() {
        super.onCreate();
        _context = getApplicationContext();
        _resource = _context.getResources();
        //是否开启debug模式
        LogUtil.getInstance().setDEBUG(true);
        //LeakCanary.install(this); //内存分析工具
    }

    public static synchronized BaseApplication context() {
        return (BaseApplication) _context;
    }

    public static Resources resources() {
        return _resource;
    }

}
