package com.example.retrofitnorestful;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view) throws IOException {

        String body = "{\"ret\": 0,\"msg\": \"123\",\"data\": {\"uid\": \"1\",\"name\": \"kkmike999\"}}";
        JSONObject json = null;
        try {
            json = new JSONObject(body);
            Toast.makeText(MainActivity.this, json.getInt("ret"), Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
