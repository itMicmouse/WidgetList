package org.micmource.appusb;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.printer.sdk.PrinterInstance;


public class MainActivity extends AppCompatActivity {

    private Button btn_select_print,btn_select_print_blu;

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
                if(printerInstance==null||printerInstance.getCurrentStatus()!=0){
                    if(printerInstance!=null){
                        printerInstance.closeConnection();
                    }
                    USBPrint4DialogFragment mUSBPrint4DialogFragment = USBPrint4DialogFragment.newInstance(new USBPrint4DialogFragment.OnClickListenerMiss() {
                        @Override
                        public void setCancle() {
                            BaseApplication.getApplication().getPrinterInstance().printText("一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十");
                        }
                    });
                    mUSBPrint4DialogFragment.show(getFragmentManager(),"DoctorAdvice2DialogFragment");
                }else {
                    PrinterInstance printerInstance1 = BaseApplication.getApplication().getPrinterInstance();
                }
            }
        });

        btn_select_print_blu = (Button) findViewById(R.id.btn_select_print_blu);





        btn_select_print_blu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
                if (adapter==null||!adapter.isEnabled()) {
                    Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableIntent, MainActivity.ENABLE_BT);
                    return;
                }
                chooseDevice();
            }
        });
    }

    private void chooseDevice() {
        BluetoothPrint4DialogFragment mUSBPrint4DialogFragment = BluetoothPrint4DialogFragment.newInstance(new BluetoothPrint4DialogFragment.OnClickListenerMiss() {
            @Override
            public void setCancle() {
                BaseApplication.getApplication().getPrinterInstance().printText("一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十");
            }
        });
        mUSBPrint4DialogFragment.show(getFragmentManager(),"DoctorAdvice2DialogFragment");
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
}
