package org.micmource.lib_sqlbrite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import rx.schedulers.Schedulers;

public class Db_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_);
    }

    public void createDb(){
        SqlBrite sqlBrite = SqlBrite.create();
//        BriteDatabase db = sqlBrite.wrapDatabaseHelper(openHelper, Schedulers.io());
    }
}
