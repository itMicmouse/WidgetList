package org.micmource.widgetlists.widgetlist;

import android.app.Application;

import com.zhiyijiankang.cloudclinck.lib_push.LibPushApplication;

/**
 * Created by yakun on 2016/10/21.
 */

public class WidgetApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LibPushApplication libPushApplication = new LibPushApplication();
        libPushApplication.onCreate(this);
    }
}