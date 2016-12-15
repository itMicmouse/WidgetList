package org.micmource.lib_greendao3.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.micmource.lib_greendao3.entity.DaoMaster;

/**
 * Created by yakun on 2016/12/15.
 */

public class MyOpenHelper extends DaoMaster.OpenHelper  {
    public MyOpenHelper(Context context, String name) {
        super(context, name);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldDbVersion, int newDbVersion) {
        DbUpdateHelper dbUpdateHelper = new DbUpdateHelper();
        db.beginTransaction();
        try {
//            switch (oldDbVersion) {
//                case 3:
//                    version324(db, dbUpdateHelper);
//                    System.out.println(oldDbVersion + "--------version324---------" + newDbVersion);
//                case 4:
//                    version425(db, dbUpdateHelper);
//                    System.out.println(oldDbVersion + "--------version425---------" + newDbVersion);
//                case 5:
//                    version526(db, dbUpdateHelper);
//                    System.out.println(oldDbVersion + "--------version526---------" + newDbVersion);
//                case 6:
//                    version627(db, dbUpdateHelper);
//                    System.out.println(oldDbVersion + "--------version627---------" + newDbVersion);
//                case 7:
//                    version728(db, dbUpdateHelper);
//                    System.out.println(oldDbVersion + "--------version728---------" + newDbVersion);
//                case 8:
//                    version829(db, dbUpdateHelper);
//                    System.out.println(oldDbVersion + "--------version829---------" + newDbVersion);
//                case 9:
//                    version9210(db, dbUpdateHelper);
//                    System.out.println(oldDbVersion + "--------version9210--------" + newDbVersion);
//                default:
//                    break;
//            }

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: 2016/12/15 上报错误
        } finally {
            db.setTransactionSuccessful();
            db.endTransaction();
        }
    }
}
