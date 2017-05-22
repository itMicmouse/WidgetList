package com.example.android7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import android.view.GestureDetector.OnGestureListener;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tv_have;
    private TextView tv_have_info;
    protected EditText et_age_calculate_year;
    private boolean ageKaiguan = true;
    private String yearStr = "0", monthStr = "0", dayStr = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_age_calculate_year = (EditText) findViewById(R.id.et_age_calculate_year);

        String yangyakun = Rsa.encryptByPublic("这是一段将要使用'秘钥字符串'进行加密的字符串!");
        System.out.println("加密后:"+yangyakun);


        tv_have = (TextView) findViewById(R.id.tv_have);
        tv_have_info = (TextView) findViewById(R.id.tv_have_info);
        tv_have.setText("lo:" + getEthernetCarrierState("lo")
                + "\ndummy0:" + getEthernetCarrierState("lo")
                + "\neth0:" + getEthernetCarrierState("eth0")
                + "\nsit0:" + getEthernetCarrierState("sit0"));
        tv_have_info.setText(getEthernetCarrierStateefc(""));
        et_age_calculate_year.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(final View view, final boolean b) {
                ageKaiguan = true;
                BaseApplication.getMainThreadHandler().post(new Runnable() {

                    @Override
                    public void run() {
                        if (b) {
                            ((EditText) view).setText("");
                        } else
                        if (TextUtils.isEmpty(et_age_calculate_year.getText())) {
                            ((EditText) view).setText("0");
                        }
                    }
                });

            }
        });
//        et_age_calculate_year.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                et_age_calculate_year.setText("");
//            }
//        });

        et_age_calculate_year.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (yearStr.equals(s.toString()) || TextUtils.isEmpty(s))
                    return;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (gestureDetector.onTouchEvent(event))
            return true;
        else
            return false;
    }


    /**
     * Called when the activity is first created.
     */

    TextView m_eventType;
    int oldevent = -1;
    private GestureDetector gestureDetector = new GestureDetector(new OnGestureListener() {

        // 鼠标按下的时候，会产生onDown。由一个ACTION_DOWN产生。
        public boolean onDown(MotionEvent event) {

            DisplayEventType("mouse down" + " " + event.getX() + "," + event.getY());
            return false;
        }

        // 用户按下触摸屏、快速移动后松开,这个时候，你的手指运动是有加速度的。
        // 由1个MotionEvent ACTION_DOWN,
        // 多个ACTION_MOVE, 1个ACTION_UP触发
        // e1：第1个ACTION_DOWN MotionEvent
        // e2：最后一个ACTION_MOVE MotionEvent
        // velocityX：X轴上的移动速度，像素/秒
        // velocityY：Y轴上的移动速度，像素/秒
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {
            DisplayEventType("onFling");
            return false;
        }

        // 用户长按触摸屏，由多个MotionEvent ACTION_DOWN触发
        public void onLongPress(MotionEvent event) {
            DisplayEventType("on long pressed");
        }

        // 滚动事件，当在触摸屏上迅速的移动，会产生onScroll。由ACTION_MOVE产生
        // e1：第1个ACTION_DOWN MotionEvent
        // e2：最后一个ACTION_MOVE MotionEvent
        // distanceX：距离上次产生onScroll事件后，X抽移动的距离
        // distanceY：距离上次产生onScroll事件后，Y抽移动的距离
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                                float distanceY) {
            DisplayEventType("onScroll" + " " + distanceX + "," + distanceY);
            return false;
        }

        //点击了触摸屏，但是没有移动和弹起的动作。onShowPress和onDown的区别在于
        //onDown是，一旦触摸屏按下，就马上产生onDown事件，但是onShowPress是onDown事件产生后，
        //一段时间内，如果没有移动鼠标和弹起事件，就认为是onShowPress事件。
        public void onShowPress(MotionEvent event) {
            DisplayEventType("pressed");

        }

        // 轻击触摸屏后，弹起。如果这个过程中产生了onLongPress、onScroll和onFling事件，就不会
        // 产生onSingleTapUp事件。
        public boolean onSingleTapUp(MotionEvent event) {
            DisplayEventType("Tap up");
            return false;
        }

    });

    private void DisplayEventType(String s) {
        Log.e("事件：", s);
    }
}
