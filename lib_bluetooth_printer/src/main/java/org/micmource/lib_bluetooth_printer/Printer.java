package org.micmource.lib_bluetooth_printer;

import android.bluetooth.BluetoothSocket;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created by yakun on 2016/8/3.
 */
public class Printer extends Thread {
    private final BluetoothSocket mmSocket;
    private final InputStream mmInStream;
    private final OutputStream mmOutStream;
    private OutputStreamWriter writer = null ;

    public interface Iprinter{
        public void printer();
    }
    private Iprinter iprinter;

    public void setIprinter(Iprinter iprinter) {
        this.iprinter = iprinter;
    }

    public Printer(BluetoothSocket socket) {
        mmSocket = socket;
        InputStream tmpIn = null;
        OutputStream tmpOut = null;
        //通过 socket 得到 InputStream 和 OutputStream
        try {
            tmpIn = socket.getInputStream();
            tmpOut = socket.getOutputStream();
        } catch (IOException e) { }

        mmInStream = tmpIn;
        mmOutStream = tmpOut;
        try {
            writer = new OutputStreamWriter(mmOutStream, "GBK");
            if(iprinter!=null){
                iprinter.printer();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        byte[] buffer = new byte[1024];  // buffer store for the stream
        int bytes; // bytes returned from read()
        try {
            initPrinter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //不断的从 InputStream 取数据
        int j=0;
        while (true) {
            if(iprinter!=null){
                iprinter.printer();
                iprinter=null;
            }
        }
    }

    public void initPrinter() throws IOException {
        writer.write(0x1B);
        writer.write(0x40);
        writer.flush();
    }
}
