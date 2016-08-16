package org.micmource.appdagger2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import org.micmource.appdagger2.adapter.UserAdapter;
import org.micmource.appdagger2.module.UserModule;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    UserAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lv_list = (ListView) findViewById(R.id.lv_list);

        // 完成注入
//        DaggerUserComponent.builder()
//                .userModule(new UserModule(this))
//                .build()
//                .inject(this);


        lv_list.setAdapter(adapter);
    }
}
