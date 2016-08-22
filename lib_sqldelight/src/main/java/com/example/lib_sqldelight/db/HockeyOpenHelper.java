package com.example.lib_sqldelight.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.GregorianCalendar;

public final class HockeyOpenHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;

    private static HockeyOpenHelper instance;

    public static HockeyOpenHelper getInstance(Context context) {
        if (instance == null) {
            instance = new HockeyOpenHelper(context);
        }
        return instance;
    }

    public HockeyOpenHelper(Context context) {
        super(context, null, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Team.CREATE_TABLE);
        db.execSQL(Player.CREATE_TABLE);

        // Populate initial data.
        long ducks = db.insert(Team.TABLE_NAME, null, Team.FACTORY.marshal()
                .coach("Randy Carlyle")
                .founded(new GregorianCalendar(1993, 3, 1))
                .name("Anaheim Ducks")
                .won_cup(true)
                .asContentValues());

        db.insert(Player.TABLE_NAME, null, Player.FACTORY.marshal()
                .first_name("Corey")
                .last_name("Perry")
                .number(10)
                .age(30)
                .birth_date(new GregorianCalendar(1985, 5, 16))
                .position(Player.Position.RIGHT_WING)
                .shoots(Player.Shoots.RIGHT)
                .weight(210)
                .team(ducks)
                .asContentValues());
        long getzlaf = db.insert(Player.TABLE_NAME, null, Player.FACTORY.marshal()
                .first_name("Ryan")
                .last_name("Getzlaf")
                .number(15)
                .age(30)
                .birth_date(new GregorianCalendar(1985, 5, 10))
                .position(Player.Position.CENTER)
                .shoots(Player.Shoots.RIGHT)
                .weight(221)
                .team(ducks)
                .asContentValues());
        db.update(Team.TABLE_NAME, Team.FACTORY.marshal().captain(getzlaf).asContentValues(),
                Team._ID + "=" + ducks, new String[0]);

        long pens = db.insert(Team.TABLE_NAME, null, Team.FACTORY.marshal()
                .coach("Mike Sullivan")
                .founded(new GregorianCalendar(1966, 2, 8))
                .name("Pittsburgh Penguins")
                .won_cup(true)
                .asContentValues());
        long crosby = db.insert(Player.TABLE_NAME, null, Player.FACTORY.marshal()
                .first_name("Sidney")
                .last_name("Crosby")
                .number(87)
                .age(28)
                .birth_date(new GregorianCalendar(1987, 8, 7))
                .position(Player.Position.CENTER)
                .shoots(Player.Shoots.LEFT)
                .weight(200)
                .team(pens)
                .asContentValues());
        db.update(Team.TABLE_NAME, Team.FACTORY.marshal().captain(crosby).asContentValues(),
                Team._ID + "=" + pens, new String[0]);

        long sharks = db.insert(Team.TABLE_NAME, null, Team.FACTORY.marshal()
                .coach("Peter DeBoer")
                .founded(new GregorianCalendar(1990, 5, 5))
                .name("San Jose Sharks")
                .won_cup(false)
                .asContentValues());
        db.insert(Player.TABLE_NAME, null, Player.FACTORY.marshal()
                .first_name("Patrick")
                .last_name("Marleau")
                .number(12)
                .age(36)
                .birth_date(new GregorianCalendar(1979, 9, 15))
                .position(Player.Position.LEFT_WING)
                .shoots(Player.Shoots.LEFT)
                .weight(220)
                .team(sharks)
                .asContentValues());
        long pavelski = db.insert(Player.TABLE_NAME, null, Player.FACTORY.marshal()
                .first_name("Joe")
                .last_name("Pavelski")
                .number(8)
                .age(31)
                .birth_date(new GregorianCalendar(1984, 7, 18))
                .position(Player.Position.CENTER)
                .shoots(Player.Shoots.RIGHT)
                .weight(194)
                .team(sharks)
                .asContentValues());
        db.update(Team.TABLE_NAME, Team.FACTORY.marshal().captain(pavelski).asContentValues(),
                Team._ID + "=" + sharks, new String[0]);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion) {
            case 1:
                db.execSQL(Team.UPDATE_COACH_FOR_TEAM, new String[]{"Randy Carlyle", "Anaheim Ducks"});
        }
    }
}
