package com.example;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;

/**
 * Created by yakunyang on 16/9/4.
 */

public class First {
    public static void main(String[] args) {
//        Test001();
        Test002();
    }
    public static void Test001() {
        try {
            File file = new File("/Users/yakunyang/workspace/WidgetList/okiostudy/okiostudys/test.txt"); //如果文件不存在，则自动创建
            BufferedSink sink = Okio.buffer(Okio.sink(file));
            sink.writeUtf8("Hello, World");
            sink.writeString("测试信息", Charset.forName("UTF-8"));
            sink.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Test002(){
        try {
            File file = new File("/Users/yakunyang/workspace/WidgetList/okiostudy/okiostudys/test.txt");

            BufferedSource source = Okio.buffer(Okio.source(file));

            //byte[] data = source.readByteArray();
            //System.out.println(new String(data, Charset.forName("UTF-8")));

            String s = source.readUtf8();
            System.out.println(s);

            BufferedSink sink = Okio.buffer(Okio.sink(new File("/Users/yakunyang/workspace/WidgetList/okiostudy/okiostudys/test2.txt")));
            source.readAll(sink);
            sink.close();

            source.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
