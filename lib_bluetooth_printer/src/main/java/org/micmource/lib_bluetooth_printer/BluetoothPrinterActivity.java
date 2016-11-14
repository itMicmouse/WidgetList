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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_printer);

        activity_open_bluetooth = (Button) findViewById(R.id.activity_open_bluetooth);
        activity_scan_bluetooth = (Button) findViewById(R.id.activity_scan_bluetooth);
        activity_find_bluetooth = (Button) findViewById(R.id.activity_find_bluetooth);
        activity_printer_bluetooth = (Button) findViewById(R.id.activity_printer_bluetooth);
        activity_create_bluetooth = (Button) findViewById(R.id.activity_create_bluetooth);
        bluetooth_device_lv = (ListView) findViewById(R.id.bluetooth_device_lv);



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
        activity_scan_bluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                //设置可被发现的时间，300s
                intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
                startActivity(intent);
            }
        });
        activity_find_bluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBluetoothAdapter.startDiscovery();
            }
        });
        activity_printer_bluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        activity_create_bluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        mPairedDevicesArrayAdapter = new ArrayAdapter<>(this,
                R.layout.device_item);
        bluetooth_device_lv.setAdapter(mPairedDevicesArrayAdapter);
        bluetooth_device_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String info = ((TextView) view).getText().toString();
                System.out.println("message:"+info);
                String address = info.substring(info.length() - 17);
                String name=info.substring(0,info.length() - 17);
                System.out.println("name:"+name);
                BluetoothDevice device = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(address);
            }
        });

        activity_break_bluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ENABLE_BT) {
            Toast.makeText(BluetoothPrinterActivity.this, "打开成功", Toast.LENGTH_SHORT).show();
        }
    }
}
