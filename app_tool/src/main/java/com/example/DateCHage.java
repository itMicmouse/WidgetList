package com.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by android on 2017/3/28.
 */

public class DateCHage {
    public static void main(String[] args) {
        String date = "2017-03-28 11:56:51";
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date date1=sdf.parse(date);
            System.out.println(sdf1.format(date1));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
