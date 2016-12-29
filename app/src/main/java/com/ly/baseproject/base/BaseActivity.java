package com.ly.baseproject.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * @author ly
 * @version V1.0
 * @Package com.ly.baseproject.base
 * @Description: 基本Activity类
 * @date 2016/11/16 14:06
 */
public class BaseActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 添加Activity到堆栈
        ActivityManager.getScreenManager().pushActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 结束Activity&从堆栈中移除
        ActivityManager.getScreenManager().popActivity(this);
    }

}
