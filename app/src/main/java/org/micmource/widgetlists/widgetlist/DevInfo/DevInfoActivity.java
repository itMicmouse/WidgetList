package org.micmource.widgetlists.widgetlist.DevInfo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.widget.TextView;

import org.micmource.widgetlists.widgetlist.R;

/**
 * Created by yakun on 2016/10/12.
 */

public class DevInfoActivity extends AppCompatActivity {
    public TextView tv_resolution;
    public TextView tv_density;
    public TextView tv_densitypdi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devinfo);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        this.tv_resolution = (TextView) findViewById(R.id.tv_resolution);
        this.tv_density = (TextView) findViewById(R.id.tv_density);
        this.tv_densitypdi = (TextView) findViewById(R.id.tv_densitypdi);
        setresolution();
    }
    private void setresolution(){
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;  // 屏幕宽度（像素）
        int height = metric.heightPixels;  // 屏幕高度（像素）
        float density = metric.density;  // 屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = metric.densityDpi;  // 屏幕密度DPI（120 / 160 / 240）

        this.tv_resolution.setText(width+"*"+height);
        this.tv_density.setText(density+"");
        this.tv_densitypdi.setText(densityDpi+"");
    }
}
