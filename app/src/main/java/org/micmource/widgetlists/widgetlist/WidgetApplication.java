package org.micmource.widgetlists.widgetlist;

import android.app.Application;

import com.zhiyijiankang.cloudclinck.lib_push.LibPushApplication;

import org.micmource.lib_greendao3.dbhelper.GreendaoApplication;

/**
 * Created by yakun on 2016/10/21.
 */

public class WidgetApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LibPushApplication libPushApplication = new LibPushApplication();
        libPushApplication.onCreate(this);
        GreendaoApplication.init(this);
    }
}
