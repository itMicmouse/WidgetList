package org.micmource.appusb;

import android.app.Application;

import com.printer.sdk.PrinterInstance;


/**
 * Created by android on 2017/3/15.
 */

public class BaseApplication  extends Application{
    // 获取到主线程的上下文
    private static BaseApplication mContext;
    private PrinterInstance printerInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static BaseApplication getApplication() {
        return mContext;
    }

    public PrinterInstance getPrinterInstance() {
        if(printerInstance!=null) {
            printerInstance.setLeftMargin(40);
            printerInstance.sendBytesData(new byte[]{29, 87, 20, 3});
        }
        return printerInstance;
    }

    public void setPrinterInstance(PrinterInstance printerInstance) {
        this.printerInstance = printerInstance;
    }
}
