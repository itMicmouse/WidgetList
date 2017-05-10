package org.micmource.appidcard;

import android.app.Application;


/**
 * Created by android on 2017/3/15.
 */

public class BaseApplication extends Application{
    // 获取到主线程的上下文
    private static BaseApplication mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static BaseApplication getApplication() {
        return mContext;
    }

}
