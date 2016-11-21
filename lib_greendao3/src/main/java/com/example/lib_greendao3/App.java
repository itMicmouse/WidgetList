package com.example.lib_greendao3;

import android.app.Application;

import com.example.lib_greendao3.entity.DaoMaster;
import com.example.lib_greendao3.entity.DaoSession;

import org.greenrobot.greendao.database.Database;

public class App  {
    /** A flag to show how easily you can switch from standard SQLite to the encrypted SQLCipher. */
    public static final boolean ENCRYPTED = true;

    private Application application;

    public App(Application application) {
        this.application = application;
    }

    private DaoSession daoSession;

    public void onCreate() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this.application,ENCRYPTED ? "notes-db-encrypted" : "notes-db");
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
