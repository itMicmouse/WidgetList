package org.micmource.appusb;

import android.app.DialogFragment;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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


import com.android.print.sdk.PrinterConstants;

import java.util.Set;

/**
 * 看病开方 遗嘱/说明 无标签
 * Created by Achen on 2017/1/16.
 */
public class BluetoothPrint4DialogFragment extends DialogFragment {

    /**
     * 蓝牙列表
     */
    private ListView mFoundDevicesListView;
    /**
     * 列表适配器
     */
    private ArrayAdapter<String> deviceArrayAdapter;
    /**
     * 搜索按钮
     */
    private Button button_scan;
    /**
     * 关闭按钮
     */
    private TextView tv_cancle;
    /**
     * 查找中
     */
    private ProgressBar pb_search;
    /**
     * 蓝牙适配器
     */
    private BluetoothAdapter mBtAdapter;
    /**
     * 蓝牙列表
     */
    private Set<BluetoothDevice> pairedDevices;

    private BluetoothPrint4DialogFragment.OnClickListenerMiss listener;

    public interface OnClickListenerMiss {
        void setCancle();
    }

    public static BluetoothPrint4DialogFragment newInstance(BluetoothPrint4DialogFragment.OnClickListenerMiss listener) {
        BluetoothPrint4DialogFragment doctorAdviceDialogFragment = new BluetoothPrint4DialogFragment();
        doctorAdviceDialogFragment.listener = listener;
        return doctorAdviceDialogFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int style = DialogFragment.STYLE_NO_TITLE;
        setStyle(style, 0);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.usb_devicelist_dialogfragment, null);
        setCancelable(true);
        findView(view);
        setListener();
        super.onResume();
        return view;
    }

    private void setListener() {
        mFoundDevicesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        deviceArrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.device_item);
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

    @Override
    public void onStop() {
        super.onStop();
        if (mBtAdapter.isDiscovering()) {
            mBtAdapter.cancelDiscovery();
            getActivity().unregisterReceiver(mReceiver);
        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case PrinterConstants.Connect.SUCCESS:
                    System.out.println("SUCCESS");
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

    private void doDiscovery() {
        pb_search.setVisibility(View.VISIBLE);
        if (mBtAdapter.isDiscovering()) {
            mBtAdapter.cancelDiscovery();
            getActivity().unregisterReceiver(mReceiver);
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

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent
                        .getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                String itemName = device.getName()
                        + " ( "
                        + getResources()
                        .getText(
                                device.getBondState() == BluetoothDevice.BOND_BONDED ? R.string.has_paired
                                        : R.string.not_paired) + " )"
                        + "\n" + device.getAddress();

                deviceArrayAdapter.remove(itemName);
                deviceArrayAdapter.add(itemName);
                mFoundDevicesListView.setEnabled(true);
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED
                    .equals(action)) {
                pb_search.setVisibility(View.INVISIBLE);
                if (deviceArrayAdapter.getCount() == 0) {
                    String noDevices = getResources().getText(
                            R.string.none_found).toString();
                    deviceArrayAdapter.add(noDevices);
                    mFoundDevicesListView.setEnabled(false);
                }
                button_scan.setEnabled(true);
            }
        }
    };


    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);

        int screen_w = wm.getDefaultDisplay().getWidth();
        int screen_h = wm.getDefaultDisplay().getHeight();
        int max = Math.max(screen_h, screen_w);
        getDialog().getWindow().setLayout((int) (max * 4 / 10), ViewGroup.LayoutParams.WRAP_CONTENT);
        applyCompat();

    }

    @Override
    public void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        getActivity().registerReceiver(mReceiver, filter);
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
