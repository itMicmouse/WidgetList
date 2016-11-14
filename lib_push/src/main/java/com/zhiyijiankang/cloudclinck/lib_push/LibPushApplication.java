package com.zhiyijiankang.cloudclinck.lib_push;

import android.app.Application;
import android.util.Log;

import cn.jpush.android.api.JPushInterface;

/**
 * For developer startup JPush SDK
 * <p>
 * 一般建议在自定义 Application 类里初始化。也可以在主 Activity 里。
 */
public class LibPushApplication {
    private static final String TAG = "JPush";

    public void onCreate(Application application) {
        JPushInterface.setDebugMode(true);    // 设置开启日志,发布时请关闭日志
        JPushInterface.init(application);     // 初始化 JPush
    }
}
