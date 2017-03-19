package org.micmource.appusb;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.printer.sdk.PrinterConstants;
import com.printer.sdk.PrinterInstance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * 看病开方 遗嘱/说明 无标签
 * Created by Achen on 2017/1/16.
 */
public class BluetoothPrint4DialogFragment extends DialogFragment {

    private ListView mFoundDevicesListView;
    private ArrayAdapter<String> deviceArrayAdapter;
    private Button button_scan;
    private TextView tv_cancle;
    private List<UsbDevice> deviceList;
    private static final String ACTION_USB_PERMISSION = "com.android.usb.USB_PERMISSION";
    private ProgressBar pb_search;

    private BluetoothAdapter mBtAdapter;

    private Set<BluetoothDevice> pairedDevices;

    private BluetoothPrint4DialogFragment.OnClickListenerMiss listener;
    public interface OnClickListenerMiss {
        void setCancle();
    }

    public static BluetoothPrint4DialogFragment newInstance( BluetoothPrint4DialogFragment.OnClickListenerMiss listener) {
        BluetoothPrint4DialogFragment doctorAdviceDialogFragment = new BluetoothPrint4DialogFragment();
        doctorAdviceDialogFragment.listener = listener;
        return doctorAdviceDialogFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int style=DialogFragment.STYLE_NO_TITLE;
        setStyle(style, 0);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.doctor_advice2_prescrible_dialogfragment, null);
        setCancelable(true);
        findView(view);
        setListener();
        return view;
    }

    private void setListener() {
        mFoundDevicesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mUSBDevice = deviceList.get(position);
                returnToPreviousActivity(deviceList.get(position));
            }
        });
        deviceArrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.device_item);
        mFoundDevicesListView.setAdapter(deviceArrayAdapter);
        button_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doDiscovery();
            }
        });
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mBtAdapter = BluetoothAdapter.getDefaultAdapter();

        pairedDevices = mBtAdapter.getBondedDevices();

        if (pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices) {
                deviceArrayAdapter.add(device.getName() + " ( "
                        + "已配对" + " )"
                        + "\n" + device.getAddress());
            }
        }

    }
    private void returnToPreviousActivity(UsbDevice mUSBDevice) {
        myPrinter = PrinterInstance.getPrinterInstance(getActivity(),mUSBDevice,mHandler);
        UsbManager mUsbManager = (UsbManager) getActivity().getSystemService(Context.USB_SERVICE);
        if (mUsbManager.hasPermission(mUSBDevice)) {
            myPrinter.openConnection();
        } else {
            // 没有权限询问用户是否授予权限
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), 0,new Intent(ACTION_USB_PERMISSION), 0);
            IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
            filter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED);
            filter.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED);
            getActivity().registerReceiver(mUsbReceiver, filter);
            mUsbManager.requestPermission(mUSBDevice, pendingIntent); // 该代码执行后，系统弹出一个对话框
        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case PrinterConstants.Connect.SUCCESS:
                    System.out.println("SUCCESS");
                    BaseApplication.getApplication().setPrinterInstance(myPrinter);
                    listener.setCancle();
                    dismiss();
                    break;
                case PrinterConstants.Connect.FAILED:
                    System.out.println("FAILED");
                    BaseApplication.getApplication().setPrinterInstance(null);
                    break;
                case PrinterConstants.Connect.CLOSED:
                    System.out.println("CLOSED");
                    BaseApplication.getApplication().setPrinterInstance(null);
                    break;
                case PrinterConstants.Connect.NODEVICE:
                    System.out.println("NODEVICE");
                    BaseApplication.getApplication().setPrinterInstance(null);
                    break;
                default:
                    break;
            }
        }
    };

    private final BroadcastReceiver mUsbReceiver = new BroadcastReceiver() {
        @SuppressLint("NewApi")
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (ACTION_USB_PERMISSION.equals(action)) {
                synchronized (this) {
                    getActivity().unregisterReceiver(mUsbReceiver);
                    UsbDevice device = intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                    if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)&& mUSBDevice.equals(device)) {
                        myPrinter.openConnection();
                    } else {
                        mHandler.obtainMessage(PrinterConstants.Connect.FAILED).sendToTarget();
                    }
                }
            }
        }
    };

    private PrinterInstance myPrinter;
    private UsbDevice mUSBDevice;

    private void doDiscovery() {
        // Indicate scanning in the title
        pb_search.setVisibility(View.VISIBLE);

        // If we're already discovering, stop it
        if (mBtAdapter.isDiscovering()) {
            mBtAdapter.cancelDiscovery();
        }
        // Request discover from BluetoothAdapter
        mBtAdapter.startDiscovery();
    }

    private void findView(View view) {
        mFoundDevicesListView = (ListView) view.findViewById(R.id.paired_devices);
        button_scan = (Button) view.findViewById(R.id.button_scan);
        tv_cancle = (TextView) view.findViewById(R.id.tv_cancle);
        pb_search = (ProgressBar) view.findViewById(R.id.pb_search);
    }







    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);

        int screen_w = wm.getDefaultDisplay().getWidth();
        int screen_h = wm.getDefaultDisplay().getHeight();
        int max = Math.max(screen_h,screen_w);
        getDialog().getWindow().setLayout((int) (max * 4 / 10), ViewGroup.LayoutParams.WRAP_CONTENT);
        applyCompat();
    }

    private void applyCompat() {
        if (Build.VERSION.SDK_INT < 19) {
            return;
        }
        getDialog().getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
