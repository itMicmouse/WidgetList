package org.micmource.lib_bluetooth_printer;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;

public class BluetoothPrinterActivity extends AppCompatActivity {

    private Button activity_open_bluetooth;
    private Button activity_scan_bluetooth;
    private Button activity_find_bluetooth;
    private Button activity_create_bluetooth;
    private Button activity_printer_bluetooth;
    private Button activity_break_bluetooth;
    private ListView bluetooth_device_lv;
    private ArrayAdapter<String> mPairedDevicesArrayAdapter;
    private Set<BluetoothDevice> pairedDevices;//已配对蓝牙
    private static final int REQUEST_ENABLE_BT = 2;
    private BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    private Printer printer;
    private ConnectThread connectThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_printer);

        activity_open_bluetooth = (Button) findViewById(R.id.activity_open_bluetooth);
        activity_open_bluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBluetoothAdapter != null) {
                    if (!mBluetoothAdapter.isEnabled()) {
                        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        startActivityForResult(intent, REQUEST_ENABLE_BT);
                    }
                } else {
                    Toast.makeText(BluetoothPrinterActivity.this, "设备不支持蓝牙", Toast.LENGTH_SHORT).show();
                }
            }
        });
        activity_scan_bluetooth = (Button) findViewById(R.id.activity_scan_bluetooth);
        activity_scan_bluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                //设置可被发现的时间，300s
                intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
                startActivity(intent);
            }
        });
        activity_find_bluetooth = (Button) findViewById(R.id.activity_find_bluetooth);
        activity_find_bluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBluetoothAdapter.startDiscovery();
            }
        });
        activity_printer_bluetooth = (Button) findViewById(R.id.activity_printer_bluetooth);
        activity_printer_bluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(printer!=null&&printer.isAlive()){
                        printer.setIprinter(new Printer.Iprinter() {
                            @Override
                            public void printer() {
                                try {
                                    printer.setAlignPosition(1);
                                    printer.printText("杨亚坤");

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                }
            }
        });
        activity_create_bluetooth = (Button) findViewById(R.id.activity_create_bluetooth);
        activity_create_bluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AcceptThread().start();
            }
        });

        bluetooth_device_lv = (ListView) findViewById(R.id.bluetooth_device_lv);
        mPairedDevicesArrayAdapter = new ArrayAdapter<String>(this,
                R.layout.device_item);
        bluetooth_device_lv.setAdapter(mPairedDevicesArrayAdapter);
        bluetooth_device_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(BluetoothPrinterActivity.this, "去配对", Toast.LENGTH_SHORT).show();
                String info = ((TextView) view).getText().toString();
                System.out.println("message:"+info);
                String address = info.substring(info.length() - 17);
                String name=info.substring(0,info.length() - 17);
                System.out.println("name:"+name);
                BluetoothDevice device = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(address);
                if(connectThread!=null&&connectThread.isAlive()){
                    Log.e("=-=-=", "onItemClick: 连接中");
                }else {
                    connectThread = new ConnectThread(device);
                    connectThread.start();
                }
            }
        });

        activity_break_bluetooth = (Button) findViewById(R.id.activity_break_bluetooth);
        activity_break_bluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AcceptThread acceptThread = new AcceptThread();
                acceptThread.cancel();
                acceptThread = null;
            }
        });



        //region设置已经配对的设备
        pairedDevices = mBluetoothAdapter.getBondedDevices();
        if (pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices) {
                mPairedDevicesArrayAdapter.add(device.getName() + " ( "
                        + getResources().getText(R.string.has_paired) + " )"
                        + "\n" + device.getAddress());
            }
        }
        //endregion


        registerBluetoothReceiver();
        registerBluetoothScanReceiver();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ENABLE_BT) {
            Toast.makeText(BluetoothPrinterActivity.this, "打开成功", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterBluetoothReceiver();
        unregisterBluetoothScanReceiver();
    }
    private class ConnectThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final BluetoothDevice mmDevice;

        public ConnectThread(BluetoothDevice device) {

            BluetoothSocket tmp = null;
            mmDevice = device;
            try {
                // 通过 BluetoothDevice 获得 BluetoothSocket 对象
                tmp = device.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
            } catch (IOException e) { }
            mmSocket = tmp;
        }

        @Override
        public void run() {
            // 建立连接前记得取消设备发现
            mBluetoothAdapter.cancelDiscovery();
            try {
                // 耗时操作，所以必须在主线程之外进行
                mmSocket.connect();
                Log.e("------------", "run: 连接成功");
            } catch (IOException connectException) {
                //处理连接建立失败的异常
                try {
                    mmSocket.close();
                } catch (IOException closeException) { }
                return;
            }
            printer = new Printer(mmSocket);
            printer.start();
        }

        //关闭一个正在进行的连接
        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) { }
        }
    }


    private class AcceptThread extends Thread {

        private final BluetoothServerSocket mmServerSocket;

        public AcceptThread() {

            BluetoothServerSocket tmp = null;
            try {
                // client 必须使用一样的 UUID !!!
                tmp = mBluetoothAdapter.listenUsingRfcommWithServiceRecord("杨亚坤iPad", UUID.randomUUID());
            } catch (IOException e) { }
            mmServerSocket = tmp;
        }

        @Override
        public void run() {
            BluetoothSocket socket = null;
            //阻塞操作
            while (true) {
                try {
                    socket = mmServerSocket.accept();
                } catch (IOException e) {
                    break;
                }
                //直到有有连接建立，才跳出死循环
                if (socket != null) {
                    //要在新开的线程执行，因为连接建立后，当前线程可能会关闭
//                    doSomething(socket);
                    try {
                        mmServerSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }

        public void cancel() {
            try {
                mmServerSocket.close();
            } catch (IOException e) { }
        }
    }



    // region 扫描打印机
    private final BroadcastReceiver mReceiverBluetoothDevice = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            // 当有设备被发现的时候会收到 action == BluetoothDevice.ACTION_FOUND 的广播
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {

                //广播的 intent 里包含了一个 BluetoothDevice 对象
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                //假设我们用一个 ListView 展示发现的设备，那么每收到一个广播，就添加一个设备到 adapter 里
                mPairedDevicesArrayAdapter.add(device.getName() + "\n" + device.getAddress());
            }
        }
    };
    public void registerBluetoothScanReceiver(){
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiverBluetoothDevice, filter); // Don't forget to unregister during onDestroy
    }
    public void  unregisterBluetoothScanReceiver(){
        unregisterReceiver(mReceiverBluetoothDevice);
    }
    //endregion

    // region 注册监控打印机的状态
    private void registerBluetoothReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);//手机蓝牙开启关闭时发送
        filter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);//蓝牙设备配对和解除配对时发送
        filter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);//蓝牙设备连接上和断开连接时发送, 这两个监听的是底层的连接状态
        filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);//断开
        registerReceiver(mReceiver, filter);
    }

    private void unregisterBluetoothReceiver() {
        unregisterReceiver(mReceiver);
    }


    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
                int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE,
                        BluetoothAdapter.ERROR);
                switch (state) {
                    case BluetoothAdapter.STATE_OFF:
                        Log.d("aaa", "STATE_OFF 手机蓝牙关闭");
                        break;
                    case BluetoothAdapter.STATE_TURNING_OFF:
                        Log.d("aaa", "STATE_TURNING_OFF 手机蓝牙正在关闭");
                        break;
                    case BluetoothAdapter.STATE_ON:
                        Log.d("aaa", "STATE_ON 手机蓝牙开启");
                        break;
                    case BluetoothAdapter.STATE_TURNING_ON:
                        Log.d("aaa", "STATE_TURNING_ON 手机蓝牙正在开启");
                        break;
                }
            }

            if (action.equals(BluetoothDevice.ACTION_BOND_STATE_CHANGED)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String name = device.getName();
                Log.d("aaa", "device name: " + name);
                int state = intent.getIntExtra(BluetoothDevice.EXTRA_BOND_STATE, -1);
                switch (state) {
                    case BluetoothDevice.BOND_NONE:
                        Log.d("aaa", "BOND_NONE 删除配对");
                        break;
                    case BluetoothDevice.BOND_BONDING:
                        Log.d("aaa", "BOND_BONDING 正在配对");
                        break;
                    case BluetoothDevice.BOND_BONDED:
                        Log.d("aaa", "BOND_BONDED 配对成功");
                        break;
                }
            }
            if (action.equals(BluetoothDevice.ACTION_ACL_CONNECTED)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                Log.d("aaa", device.getName() + " ACTION_ACL_CONNECTED");
            } else if (action.equals(BluetoothDevice.ACTION_ACL_DISCONNECTED)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                Log.d("aaa", device.getName() + " ACTION_ACL_DISCONNECTED");
            }
        }
    };
    //endregion
}
