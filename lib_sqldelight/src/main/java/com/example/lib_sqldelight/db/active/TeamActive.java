package com.example.lib_sqldelight.db.active;

import android.database.sqlite.SQLiteDatabase;

import com.example.lib_sqldelight.db.Team;

import java.util.GregorianCalendar;

/**
 * Created by yakunyang on 16/8/22.
 */
public class TeamActive {
    public static long insert(SQLiteDatabase db, String coach, int year,int mourch,int day, String name,boolean won_cup) {
        long insert = db.insert(Team.TABLE_NAME, null, Team.FACTORY.marshal()
                .coach(coach)
                .founded(new GregorianCalendar(year, mourch, day))
                .name(name)
                .won_cup(won_cup)
                .asContentValues());
        return insert;
    }
}
