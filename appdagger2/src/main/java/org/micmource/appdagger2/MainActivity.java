package org.micmource.appdagger2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    private TextView tv_name;

    @Inject
    DaggerPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_name = (TextView) findViewById(R.id.tv_name);

        injectA();

        presenter.setUserName();
    }

    private void injectA(){
        DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .build()
                .inject(this);
    }

    public void showUserName(String name) {
        tv_name.setText(name);
    }
}
