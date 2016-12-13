package org.micmource.lib_greendao3.greendaoapp;

import android.app.Application;

import org.greenrobot.greendao.database.Database;
import org.micmource.lib_greendao3.entity.DaoMaster;
import org.micmource.lib_greendao3.entity.DaoSession;

/**
 * Created by yakunyang on 2016/12/13.
 */

public class GreendaoApplication {
    /** A flag to show how easily you can switch from standard SQLite to the encrypted SQLCipher. */
    public static final boolean ENCRYPTED = false;

    private static DaoSession daoSession;

    public void onCreate(Application application) {

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(application, ENCRYPTED ? "notes-db-encrypted" : "notes-db");
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }
}
