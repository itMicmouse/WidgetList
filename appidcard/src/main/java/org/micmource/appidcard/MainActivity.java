package org.micmource.appidcard;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zkteco.android.IDReader.IDPhotoHelper;
import com.zkteco.android.IDReader.WLTService;
import com.zkteco.android.biometric.core.device.ParameterHelper;
import com.zkteco.android.biometric.core.device.TransportType;
import com.zkteco.android.biometric.core.utils.LogHelper;
import com.zkteco.android.biometric.module.idcard.IDCardReader;
import com.zkteco.android.biometric.module.idcard.IDCardReaderFactory;
import com.zkteco.android.biometric.module.idcard.exception.IDCardReaderException;
import com.zkteco.android.biometric.module.idcard.meta.IDCardInfo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class MainActivity extends Activity {
    private boolean bopen = false;
    private TextView textView;
    private ImageView imageView;
    private ImageView imageView2;
    private IDCardReader idCardReader = null;

    private static final int VID = 1024;    //IDR VID
    private static final int PID = 50010;     //IDR PID

    private static final String ACTION_USB_PERMISSION = "com.android.usb.USB_PERMISSION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView2 = (ImageView) findViewById(R.id.imageView2);


        findViewById(R.id.btn_select_print).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                USBPrint4DialogFragment mUSBPrint4DialogFragment = USBPrint4DialogFragment.newInstance(new USBPrint4DialogFragment.OnClickListenerMiss() {
                    @Override
                    public void setCancle() {
//
                    }
                });
                mUSBPrint4DialogFragment.show(getFragmentManager(), "DoctorAdvice2DialogFragment");
            }
        });
    }

    private UsbDevice usbDevice = null;

    private void startIDCardReader() {
        // Define output log level
        LogHelper.setLevel(Log.VERBOSE);
        // Start fingerprint sensor

        UsbManager mUsbManager = (UsbManager) getSystemService(Context.USB_SERVICE);

        HashMap<String, UsbDevice> deviceList = mUsbManager.getDeviceList();
        Iterator<Map.Entry<String, UsbDevice>> iterator = deviceList.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, UsbDevice> next = iterator.next();
            String key = next.getKey();
            UsbDevice value = next.getValue();
            int vendorId = value.getVendorId();
            int productId = value.getProductId();
            if(vendorId==VID&&productId==PID){
                usbDevice = value;
                break;
            }
        }
        if(usbDevice!=null){
            if (mUsbManager.hasPermission(usbDevice)) {
                //业务
                getReader();
            } else {
                // 没有权限询问用户是否授予权限
                PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0,new Intent(ACTION_USB_PERMISSION), 0);
                IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
                filter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED);
                filter.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED);
                registerReceiver(mUsbReceiver, filter);
                mUsbManager.requestPermission(usbDevice, pendingIntent); // 该代码执行后，系统弹出一个对话框
            }
        }
    }

    private void getReader(){
        Map idrparams = new HashMap();
        idrparams.put(ParameterHelper.PARAM_KEY_VID, VID);
        idrparams.put(ParameterHelper.PARAM_KEY_PID, PID);
        idCardReader = IDCardReaderFactory.createIDCardReader(this, TransportType.USB, idrparams);
    }

    public void OnBnOpen(View view) {
        startIDCardReader();
        try {
            if (bopen) return;
            idCardReader.open(0);
            bopen = true;
            textView.setText("连接设备成功");
        } catch (IDCardReaderException e) {
            textView.setText("关闭设备成功");
            LogHelper.d("连接设备失败, 错误码：" + e.getErrorCode() + "\n错误信息：" + e.getMessage() + "\n 内部错误码=" + e.getInternalErrorCode());
        }
    }

    public void OnBnClose(View view) {
        try {
            if (bopen) {
                idCardReader.close(0);
                bopen = false;
            }
            textView.setText("关闭设备成功");
        } catch (IDCardReaderException e) {
            textView.setText("关闭设备失败");
            LogHelper.d("关闭设备失败, 错误码：" + e.getErrorCode() + "\n错误信息：" + e.getMessage() + "\n 内部错误码=" + e.getInternalErrorCode());
        }
    }

    public void OnBnGetSamID(View view) {
        try {
            if (!bopen) {
                textView.setText("请先连接设备");
            }
            String samid = idCardReader.getSAMID(0);
            textView.setText("获取SAM编号成功：" + samid);
        } catch (IDCardReaderException e) {
            textView.setText("获取SAM编号失败");
            LogHelper.d("获取SAM模块失败, 错误码：" + e.getErrorCode() + "\n错误信息：" + e.getMessage() + "\n 内部错误码=" + e.getInternalErrorCode());
        }
    }

    public void OnBnFind(View view) {
        try {
            if (!bopen) {
                textView.setText("请先连接设备");
            }
            idCardReader.findCard(0);
            textView.setText("寻卡成功");
        } catch (IDCardReaderException e) {
            textView.setText("寻卡失败");
            LogHelper.d("寻卡失败, 错误码：" + e.getErrorCode() + "\n错误信息：" + e.getMessage() + "\n 内部错误码=" + e.getInternalErrorCode());
        }
    }

    public void OnBnSelect(View view) {
        try {
            if (!bopen) {
                textView.setText("请先连接设备");
            }
            idCardReader.selectCard(0);
            textView.setText("选卡成功");
        } catch (IDCardReaderException e) {
            textView.setText("选卡失败");
            LogHelper.d("选卡失败, 错误码：" + e.getErrorCode() + "\n错误信息：" + e.getMessage() + "\n 内部错误码=" + e.getInternalErrorCode());
        }
    }

    public void OnBnRead(View view) {
        try {
            if (!bopen) {
                textView.setText("请先连接设备");
            }
            IDCardInfo idCardInfo = new IDCardInfo();
            boolean ret = idCardReader.readCard(0, 1, idCardInfo);

            if (ret) {
                textView.setText("姓名：" + idCardInfo.getName() + "，民族：" + idCardInfo.getNation() + "，住址：" + idCardInfo.getAddress() + ",身份证号：" + idCardInfo.getId());
                if (idCardInfo.getPhotolength() > 0) {
                    byte[] buf = new byte[WLTService.imgLength];
                    if (1 == WLTService.wlt2Bmp(idCardInfo.getPhoto(), buf)) {
                        imageView.setImageBitmap(IDPhotoHelper.Bgr2Bitmap(buf));
                    }
                }
                if(idCardInfo.getFplength()>0){
                    byte[] buf = new byte[WLTService.imgLength];
                    if (1 == WLTService.wlt2Bmp(idCardInfo.getFpdata(), buf)) {
                        imageView.setImageBitmap(IDPhotoHelper.Bgr2Bitmap(buf));
                    }
                }
            }
        } catch (IDCardReaderException e) {
            textView.setText("读卡失败");
            LogHelper.d("读卡失败, 错误码：" + e.getErrorCode() + "\n错误信息：" + e.getMessage() + "\n 内部错误码=" + e.getInternalErrorCode());
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private final BroadcastReceiver mUsbReceiver = new BroadcastReceiver() {
        @SuppressLint("NewApi")
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (ACTION_USB_PERMISSION.equals(action)) {
                synchronized (this) {
                    unregisterReceiver(mUsbReceiver);
                    UsbDevice device = intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                    if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)&& usbDevice.equals(device)) {
                        //业务
                        getReader();
                    } else {
                        //失败
                        Toast.makeText(context, "失败", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    };
}
