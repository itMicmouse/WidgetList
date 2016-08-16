package org.micmource.lib_sqlbrite.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yakun on 2016/8/15.
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;

    public MySQLiteOpenHelper(Context context) {
        super(context, "todo.db", null /* factory */, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
