package com.example.android7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private TextView tv_have;
    private TextView tv_have_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_have = (TextView) findViewById(R.id.tv_have);
        tv_have_info = (TextView) findViewById(R.id.tv_have_info);
        tv_have.setText("lo:"+getEthernetCarrierState("lo")
                +"\ndummy0:"+getEthernetCarrierState("lo")
                +"\neth0:"+getEthernetCarrierState("eth0")
                +"\nsit0:"+getEthernetCarrierState("sit0"));
        tv_have_info.setText(getEthernetCarrierStateefc(""));
    }


    private int getEthernetCarrierState(String iface) {
        if (iface != "") {
            try {
                File file = new File("/sys/class/net/" + iface + "/carrier");
                String carrier = ReadFromFile(file);
                int carrier_state = Integer.parseInt(carrier);
                return carrier_state;
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        } else {
            return 0;
        }
    }
    private String getEthernetCarrierStateefc(String iface) {
        if (iface != "") {
            try {
                File file = new File("/proc/net/dev");
                String carrier = ReadFromFile(file);
                return carrier;
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        } else {
            return "";
        }
    }

    private String ReadFromFile(File file) {
        StringBuilder sb = new StringBuilder();
        try {
            String encoding = "GBK";
            if (file.isFile() && file.exists()) { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    sb.append(lineTxt);
                }
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return sb.toString();
    }
}
