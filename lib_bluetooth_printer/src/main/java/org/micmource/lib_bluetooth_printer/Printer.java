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

    //向 Server 写入数据
    public void write(byte[] bytes) {
        try {
            mmOutStream.write(bytes);
        } catch (IOException e) { }
    }

    public void cancel() {
        try {
            mmSocket.close();
        } catch (IOException e) { }
    }

    public void initPrinter() throws IOException {
        writer.write(0x1B);
        writer.write(0x40);
        writer.flush();
    }
    public void printText(String text) throws IOException {
        writer.write(text);
        writer.flush();
    }

    /**
     * 设置文本对齐方式
     * @param align 打印位置  0：居左(默认) 1：居中 2：居右
     * @throws IOException
     */
    public void setAlignPosition(int align) throws IOException {
        writer.write(0x1B);
        writer.write(0x61);
        writer.write(align);
        writer.flush();
    }
}
