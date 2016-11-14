package com.zhiyijiankang.cloudclinck.appbutterknife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
//import android.widget.TextView;
//import android.widget.Toast;

//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

//    @BindView(R.id.hello)
//    TextView hello;
//
//    @OnClick(R.id.hello)
//    void hello() {
//        Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);
//        hello.setText("yangyakun");
    }
}
