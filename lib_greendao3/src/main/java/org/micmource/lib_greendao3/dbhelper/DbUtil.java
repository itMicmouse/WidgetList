package org.micmource.lib_greendao3.dbhelper;

import org.greenrobot.greendao.rx.RxDao;
import org.micmource.lib_greendao3.dbhelper.impl.NameHelper;
import org.micmource.lib_greendao3.entity.Name;
import org.micmource.lib_greendao3.entity.NameDao;

/**
 * Created by yakun on 2016/12/15.
 */

public class DbUtil {
    private static NameHelper nameHelper;

    public static RxDao<Name, Long> getDriverDaorx() {
        return GreendaoApplication.getDaoSession().getNameDao().rx();
    }
    public static NameDao getDriverDao() {
        return GreendaoApplication.getDaoSession().getNameDao();
    }
    public static NameHelper getNameHelper() {
        if (nameHelper == null) {
            nameHelper = new NameHelper(getDriverDao());
        }
        return nameHelper;
    }
}
