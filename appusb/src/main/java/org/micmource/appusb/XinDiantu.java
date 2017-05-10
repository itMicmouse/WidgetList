package org.micmource.appusb;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by android on 2017/3/21.
 */

public class XinDiantu {
    public static ArrayList<ArrayList> getPrintByty(Context context) throws IOException {
        String Date = "";
        String fromAssets = getFromAssets("code.txt", context);
        byte[] decode = Base64.decode(fromAssets, Base64.DEFAULT);
        ArrayList<ArrayList> dateList = getDateList(decode);
        return dateList;
    }

    public static ArrayList<ArrayList> getDateList(byte[] decode){
        ArrayList<Byte> date1 = new ArrayList<>();
        ArrayList<Byte> date2 = new ArrayList<>();
        ArrayList<Byte> date3 = new ArrayList<>();
        ArrayList<Byte> date4 = new ArrayList<>();
        ArrayList<Byte> date5 = new ArrayList<>();
        ArrayList<Byte> date6 = new ArrayList<>();
        ArrayList<Byte> date7 = new ArrayList<>();
        ArrayList<Byte> date8 = new ArrayList<>();
        ArrayList<Byte> date9 = new ArrayList<>();
        ArrayList<Byte> date10 = new ArrayList<>();
        ArrayList<Byte> date11= new ArrayList<>();
        ArrayList<Byte> date12= new ArrayList<>();
        ArrayList<Byte> date13= new ArrayList<>();

        ArrayList<ArrayList> list = new ArrayList<>();

        list.add(date1 );
        list.add(date2 );
        list.add(date3 );
        list.add(date4 );
        list.add(date5 );
        list.add(date6 );
        list.add(date7 );
        list.add(date8 );
        list.add(date9 );
        list.add(date10);
        list.add(date11);
        list.add(date12);
        list.add(date13);

        for (int i = 0; i < decode.length; i++) {
            switch (i%13){
                case 0:
                    date1.add(decode[i]);
                    break;
                case 1:
                    date2.add(decode[i]);
                    break;
                case 2:
                    date3.add(decode[i]);
                    break;
                case 3:
                    date4.add(decode[i]);
                    break;
                case 4:
                    date5.add(decode[i]);
                    break;
                case 5:
                    date6.add(decode[i]);
                    break;
                case 6:
                    date7.add(decode[i]);
                    break;
                case 7:
                    date8.add(decode[i]);
                    break;
                case 8:
                    date9.add(decode[i]);
                    break;
                case 9:
                    date10.add(decode[i]);
                    break;
                case 10:
                    date11.add(decode[i]);
                    break;
                case 11:
                    date12.add(decode[i]);
                    break;
                case 12:
                    date13.add(decode[i]);
                    break;
                default:

            }
        }
        return list;
    }

    public static String getFromAssets(String fileName, Context context){
        try {
            InputStreamReader inputReader = new InputStreamReader(context.getResources().getAssets().open(fileName) );
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line="";
            String Result="";
            while((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    /**
     * 16进制的字符串表示转成字节数组
     *
     * @param hexString 16进制格式的字符串
     * @return 转换后的字节数组
     **/
    public static byte[] toByteArray(String hexString) {
        if (TextUtils.isEmpty(hexString))
            throw new IllegalArgumentException("this hexString must not be empty");

        hexString = hexString.toLowerCase();
        final byte[] byteArray = new byte[hexString.length() / 2];
        int k = 0;
        for (int i = 0; i < byteArray.length; i++) {//因为是16进制，最多只会占用4位，转换成字节需要两个16进制的字符，高位在先
            byte high = (byte) (Character.digit(hexString.charAt(k), 16) & 0xff);
            byte low = (byte) (Character.digit(hexString.charAt(k + 1), 16) & 0xff);
            byteArray[i] = (byte) (high << 4 | low);
            k += 2;
        }
        return byteArray;
    }
}
