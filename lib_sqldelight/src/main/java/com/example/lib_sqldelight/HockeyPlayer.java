package com.example.lib_sqldelight;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.example.lib_sqldelight.data.HockeyPlayerModel;
import com.google.auto.value.AutoValue;
import com.squareup.sqldelight.RowMapper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yakun on 2016/8/22.
 */
@AutoValue
public abstract class HockeyPlayer implements HockeyPlayerModel {
    public static final Factory<HockeyPlayer> FACTORY = new Factory<>(new Creator<HockeyPlayer>() {
        @Override
        public HockeyPlayer create(long _id, long number, String name) {
            return new AutoValue_HockeyPlayer(_id, number, name);
        }

    });

    public static final RowMapper<HockeyPlayer> MAPPER = FACTORY.select_by_nameMapper();
    public static final RowMapper<String> PLAYER_NAMES_MAPPER = FACTORY.player_namesMapper();

    public List<String> playerNames(SQLiteDatabase db) {
        List<String> names = new ArrayList<>();
        try {
            Cursor cursor = db.rawQuery(PLAYER_NAMES, null);
            while (cursor.moveToNext()) {
                names.add(PLAYER_NAMES_MAPPER.map(cursor));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return names;
    }

    public static final RowMapper<NamesForNumber> NAMES_FOR_NUMBER_MAPPER =
            FACTORY.names_for_numberMapper(new Names_for_numberCreator<NamesForNumber>() {
                @Override
                public NamesForNumber create(long number, @NonNull String group_concat_name) {
                    return new AutoValue_HockeyPlayer_NamesForNumber(number, group_concat_name);
                }
            });

    public Map<Long, String[]> namesForNumber(SQLiteDatabase db) {
        Map<Long, String[]> namesForNumberMap = new LinkedHashMap<>();
        try {
            Cursor cursor = db.rawQuery(NAMES_FOR_NUMBER, null);
            while (cursor.moveToNext()) {
                NamesForNumber namesForNumber = NAMES_FOR_NUMBER_MAPPER.map(cursor);
                namesForNumberMap.put(namesForNumber.number(), namesForNumber.names());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return namesForNumberMap;
    }

    @AutoValue
    public static abstract class NamesForNumber implements Names_for_numberModel {
        public String[] names() {
            return group_concat_name().split(",");
        }
    }

    public void insert(SQLiteDatabase db, long _id, long number, String name) {
        db.insert(HockeyPlayer.TABLE_NAME, null, HockeyPlayer.FACTORY.marshal()
                ._id(_id)
                .number(number)
                .name(name)
                .asContentValues());
    }

    public List<HockeyPlayer> alecs(SQLiteDatabase db) {
        List<HockeyPlayer> result = new ArrayList<>();
        try {
            Cursor cursor = db.rawQuery(HockeyPlayer.SELECT_BY_NAME, new String[]{"Alec"});
            while (cursor.moveToNext()) {
                result.add(HockeyPlayer.MAPPER.map(cursor));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
