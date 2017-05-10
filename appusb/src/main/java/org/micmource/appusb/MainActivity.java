package org.micmource.appusb;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.print.sdk.PrinterInstance;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;


public class MainActivity extends Activity {

    private static ArrayList<ArrayList> printByty;
    private Button btn_select_print, btn_select_print_blu;

    public static final int ENABLE_BT = 2; // 申请打开蓝牙

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_select_print = (Button) findViewById(R.id.btn_select_print);

        btn_select_print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrinterInstance printerInstance = BaseApplication.getApplication().getPrinterInstance();
                if (printerInstance == null) {
                    if (printerInstance != null) {
                        printerInstance.closeConnection();
                    }
                    USBPrint4DialogFragment mUSBPrint4DialogFragment = USBPrint4DialogFragment.newInstance(new USBPrint4DialogFragment.OnClickListenerMiss() {
                        @Override
                        public void setCancle() {
//                            BaseApplication.getApplication().getPrinterInstance().printImage(createJian("一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十"));
                            print();
                        }
                    });
                    mUSBPrint4DialogFragment.show(getFragmentManager(), "DoctorAdvice2DialogFragment");
                } else {
//                    BaseApplication.getApplication().getPrinterInstance().printImage(createJian("一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十"));
                    print();
                }
            }
        });

        btn_select_print_blu = (Button) findViewById(R.id.btn_select_print_blu);


        btn_select_print_blu.setOnClickListener(new View.OnClickListener() {
            private ArrayList<ArrayList> printByty;

            @Override
            public void onClick(View v) {
                BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
                if (adapter == null || !adapter.isEnabled()) {
                    Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableIntent, MainActivity.ENABLE_BT);
                    return;
                }
                chooseDevice();
            }
        });
    }

    private void print() {
        PrinterInstance printerInstance = BaseApplication.getApplication().getPrinterInstance();
//        Bitmap jianLine = createJianLine(printerInstance);
//        printerInstance.printImage(jianLine);
//        String[] bytesnongdu = new String[]{"1D","28","45","04","00","05","05","6","01"};
//        for (String s : bytesnongdu) {
//            byte[] bytes = XinDiantu.toByteArray(s);
//            printerInstance.sendByteData(bytes);
//        }

//        String[] bytes = new String[]{
//                //"1D","28","45","04","00","05","05","6","01",
//                "16","5A",
//                "00", "18", "00 ", "ff","ff","ff", "ff", "ff", "ff","ff",
//                "ff", "ff", "ff", "ff","ff","ff", "ff", "ff", "ff","ff",
//                "ff", "ff", "ff", "ff","ff","ff", "ff", "00", "00","00",
//                "00", "00", "00", "00","00","00", "00", "00", "00","00",
//                "00", "00", "00", "00","00","00", "00", "00", "00","00",
//                "ff", "ff", "ff", "ff","ff","ff", "ff", "ff", "ff","ff",
//                "ff", "ff", "ff", "ff","ff","ff", "ff", "ff", "ff","00",
//                "ff", "ff", "ff", "ff","ff","ff", "00", "ff", "ff","ff",
//                "ff", "ff", "ff", "ff","ff","ff", "00", "ff", "ff","ff",
//                "15","001"};
//        for (int i = 0; i < bytes.length; i++) {
//            if(i==3){
//                byte[] bytes1 = XinDiantu.toByteArray("80");
//                printerInstance.sendByteData(bytes1);
//            }else {
//                byte[] bytes1 = XinDiantu.toByteArray(bytes[i]);
//                printerInstance.sendByteData(bytes1);
//            }
//        }
//        for (int i = 0; i < bytes.length; i++) {
//            if(i==3){
//                byte[] bytes1 = XinDiantu.toByteArray("40");
//                printerInstance.sendByteData(bytes1);
//            }else {
//                byte[] bytes1 = XinDiantu.toByteArray(bytes[i]);
//                printerInstance.sendByteData(bytes1);
//            }
//        }
//        for (int i = 0; i < bytes.length; i++) {
//            if(i==3){
//                byte[] bytes1 = XinDiantu.toByteArray("20");
//                printerInstance.sendByteData(bytes1);
//            }else {
//                byte[] bytes1 = XinDiantu.toByteArray(bytes[i]);
//                printerInstance.sendByteData(bytes1);
//            }
//        }
//        for (int i = 0; i < bytes.length; i++) {
//            if(i==3){
//                byte[] bytes1 = XinDiantu.toByteArray("10");
//                printerInstance.sendByteData(bytes1);
//            }else {
//                byte[] bytes1 = XinDiantu.toByteArray(bytes[i]);
//                printerInstance.sendByteData(bytes1);
//            }
//        }
//        for (int i = 0; i < bytes.length; i++) {
//            if(i==3){
//                byte[] bytes1 = XinDiantu.toByteArray("8");
//                printerInstance.sendByteData(bytes1);
//            }else {
//                byte[] bytes1 = XinDiantu.toByteArray(bytes[i]);
//                printerInstance.sendByteData(bytes1);
//            }
//        }
//        for (int i = 0; i < bytes.length; i++) {
//            if(i==3){
//                byte[] bytes1 = XinDiantu.toByteArray("4");
//                printerInstance.sendByteData(bytes1);
//            }else {
//                byte[] bytes1 = XinDiantu.toByteArray(bytes[i]);
//                printerInstance.sendByteData(bytes1);
//            }
//        }
//        for (int i = 0; i < bytes.length; i++) {
//            if(i==3){
//                byte[] bytes1 = XinDiantu.toByteArray("2");
//                printerInstance.sendByteData(bytes1);
//            }else {
//                byte[] bytes1 = XinDiantu.toByteArray(bytes[i]);
//                printerInstance.sendByteData(bytes1);
//            }
//        }
//        for (int i = 0; i < bytes.length; i++) {
//            if(i==3){
//                byte[] bytes1 = XinDiantu.toByteArray("1");
//                printerInstance.sendByteData(bytes1);
//            }else {
//                byte[] bytes1 = XinDiantu.toByteArray(bytes[i]);
//                printerInstance.sendByteData(bytes1);
//            }
//        }
        byte[] bytes = initLine1(580, 1);
        printerInstance.sendByteData(bytes);


    }

    private void chooseDevice() {
        BluetoothPrint4DialogFragment mUSBPrint4DialogFragment = BluetoothPrint4DialogFragment.newInstance(new BluetoothPrint4DialogFragment.OnClickListenerMiss() {
            @Override
            public void setCancle() {
//                BaseApplication.getApplication().getPrinterInstance().printText("一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十");
            }
        });
        mUSBPrint4DialogFragment.show(getFragmentManager(), "DoctorAdvice2DialogFragment");
    }

    private static Bitmap createJian(String testString) {
        if (testString.length() > 15) {
            testString = testString.substring(0, 15);
        }
        Bitmap bitmap = Bitmap.createBitmap(240, 240, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);
        paint.setColor(Color.BLACK);
        paint.setTextSize(30);
        canvas.drawRect(640, 10, 770, 95, paint);

        Paint painttext = new Paint();
        painttext.setStyle(Paint.Style.FILL);
        painttext.setColor(Color.BLACK);
        painttext.setTextSize(20);
        canvas.drawText("门诊处方", 665, 40, painttext);
        canvas.drawLine(640, 53, 770, 53, paint);

        canvas.drawText("当日有效", 665, 85, painttext);

        String clinic = "处方笺";

        Rect targetRect = new Rect(0, 0, 630, 60);
        Paint paintBig = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintBig.setTextSize(40);
        paintBig.setColor(Color.BLACK);
        Paint.FontMetricsInt fontMetrics = paintBig.getFontMetricsInt();
        // 转载请注明出处：http://blog.csdn.net/hursing
        int baseline = (targetRect.bottom + targetRect.top - fontMetrics.bottom - fontMetrics.top) / 2;
        // 下面这行是实现水平居中，drawText对应改为传入targetRect.centerX()
        paintBig.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(testString, targetRect.centerX(), baseline, paintBig);
        paintBig.setTextSize(35);
        Rect targetRectJian = new Rect(0, 60, 630, 120);
        int baselineJian = (targetRectJian.bottom + targetRectJian.top - fontMetrics.bottom - fontMetrics.top) / 2;
        canvas.drawText(clinic, targetRectJian.centerX(), baselineJian, paintBig);

        return bitmap;
    }

    private static Bitmap createJianLine(PrinterInstance printerInstance) {
        Bitmap bitmap = Bitmap.createBitmap(200, 1500, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);
        paint.setColor(Color.BLACK);
        paint.setTextSize(30);
        try {
            printByty = XinDiantu.getPrintByty(BaseApplication.getApplication());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Byte> arrayList = printByty.get(0);
        for (int i = 0; i <arrayList.size(); i++) {
            Byte aByte = arrayList.get(i);
            canvas.drawPoint(100+arrayList.get(i),i , paint);
        }
        canvas.drawLine(50,0,150,0,paint);
        canvas.drawLine(50,0,300,0,paint);


        return bitmap;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case ENABLE_BT:
                if (resultCode == Activity.RESULT_OK) {
                    chooseDevice();
                } else {
                    Toast.makeText(this, "请打开蓝牙来进行打印功能", Toast.LENGTH_SHORT).show();
                }
            default:
        }
    }

    public static byte[] initLine1(int w, int type){
        byte[][] kk = new byte[][]{
                {0x00,0x00,0x7c,0x7c,0x7c,0x00,0x00},
                {0x00,0x00,(byte) 0xff,(byte) 0xff,(byte) 0xff,0x00,0x00},
                {0x00,0x44,0x44,(byte) 0xff,0x44,0x44,0x00},
                {0x00,0x22,0x55,(byte) 0x88,0x55,0x22,0x00},
                {0x08,0x08,0x1c,0x7f,0x1c,0x08,0x08},
                {0x08,0x14,0x22,0x41,0x22,0x14,0x08},
                {0x08,0x14,0x2a,0x55,0x2a,0x14,0x08},
                {0x08,0x1c,0x3e,0x7f,0x3e,0x1c,0x08},
                {0x49,0x22,0x14,0x49,0x14,0x22,0x49},
                {0x49,0x22,0x14,0x49,0x14,0x22,0x49},
                {0x49,0x22,0x14,0x49,0x14,0x22,0x49},
                {0x49,0x22,0x14,0x49,0x14,0x22,0x49},
                {0x63,0x77,0x3e,0x1c,0x3e,0x77,0x63},
                {0x70,0x20,(byte) 0xaf,(byte) 0xaa,(byte) 0xfa,0x02,0x07},
                {(byte) 0xef,0x28,(byte) 0xee,(byte) 0xaa,(byte) 0xee,(byte) 0x82,(byte) 0xfe},
        };

        int ww = (w + 7)/8;

        byte[] data = new byte[ 13 * ww + 8];

        data[0] = 0x1D;
        data[1] = 0x76;
        data[2] = 0x30;
        data[3] = 0x00;

        data[4] = (byte)ww;//xL
        data[5] = (byte)(ww >> 8);//xH
        data[6] = 4;  //高度13
        data[7] = 9;

        int k = 8;
        for(int i=0; i < 3 * ww; i++){
            data[k++] = 0;
        }
        for(int i=0; i < ww; i++){
            data[k++] = kk[type][0];
        }
        for(int i=0; i < ww; i++){
            data[k++] = kk[type][1];
        }
        for(int i=0; i < ww; i++){
            data[k++] = kk[type][2];
        }
        for(int i=0; i < ww; i++){
            data[k++] = kk[type][3];
        }
        for(int i=0; i < ww; i++){
            data[k++] = kk[type][4];
        }
        for(int i=0; i < ww; i++){
            data[k++] = kk[type][5];
        }
        for(int i=0; i < ww; i++){
            data[k++] = kk[type][6];
        }
        for(int i=0; i < 3 * ww; i++){
            data[k++] = 0;
        }
        return data;
    }
}
