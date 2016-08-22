package org.micmource.appdagger2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import org.micmource.appdagger2.adapter.UserAdapter;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    UserAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lv_list = (ListView) findViewById(R.id.lv_list);


        lv_list.setAdapter(adapter);
    }
}
