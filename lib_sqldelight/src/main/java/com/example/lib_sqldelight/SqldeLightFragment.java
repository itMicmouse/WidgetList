package com.example.lib_sqldelight;

import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lib_sqldelight.db.HockeyOpenHelper;
import com.example.lib_sqldelight.db.Team;

/**
 * Created by yakun on 2016/8/22.
 */
public class SqldeLightFragment extends Fragment {


    private EditText et_name;
    private EditText et_number;
    private EditText et_select_number;


    private SQLiteDatabase db;
    private ListView lv_all;
    private Adapter adapter;

    public SqldeLightFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.sqlfragment, null);
        inflate.setBackgroundColor(Color.WHITE);
        return inflate;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.et_name = (EditText) view.findViewById(R.id.et_name);
        this.et_number = (EditText) view.findViewById(R.id.et_number);
        this.et_select_number = (EditText) view.findViewById(R.id.et_select_number);
        view.findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "添加", Toast.LENGTH_SHORT).show();

            }
        });
        view.findViewById(R.id.btn_select).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "查询", Toast.LENGTH_SHORT).show();
            }
        });

        lv_all = (ListView) view.findViewById(R.id.lv_all);

        SQLiteDatabase db = HockeyOpenHelper.getInstance(getActivity()).getReadableDatabase();
        Cursor teamsCursor = db.rawQuery(Team.SELECT_ALL, new String[0]);
//        adapter = new Adapter(this, teamsCursor);
//        teams.setAdapter(adapter);
    }


    private static final class Adapter extends CursorAdapter {
        public Adapter(Context context, Cursor c) {
            super(context, c, false);
        }

        @Override public View newView(Context context, Cursor cursor, ViewGroup parent) {
            return View.inflate(context, R.layout.team_row, null);
        }

        @Override public void bindView(View view, Context context, Cursor cursor) {
            ((TeamRow) view).populate(Team.MAPPER.map(cursor));
        }
    }

}