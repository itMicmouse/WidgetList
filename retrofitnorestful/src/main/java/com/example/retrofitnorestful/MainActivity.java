package com.example.retrofitnorestful;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.retrofitnorestful.net.UserService;
import com.example.retrofitnorestful.net.domain.User;
import com.example.retrofitnorestful.utils.RetrofitUtils;


import java.io.IOException;

/**
 * Created by yakun on 2016/8/2.
 */
public class MainActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void click(View view) throws IOException {
        new Thread(){
            @Override
            public void run() {
                UserService api = RetrofitUtils.createApi(UserService.class);
                User name1 = null;
                try {
                    name1 = api.loadUser("name").execute().body();
                    System.out.println(name1.getName());
                    Log.e("MainActivity.class",name1.getName());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
