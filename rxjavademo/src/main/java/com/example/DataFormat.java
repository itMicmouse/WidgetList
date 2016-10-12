package com.example;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by yakun on 2016/9/30.
 */

public class DataFormat {
    public static void main(String[] args) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("MM-dd a HH:mm");
        System.out.println(format.format(date));

        System.out.println(getUUID());
        System.out.println(getUUID());
    }

    public static String getUUID(){

        java.util.UUID uuid = java.util.UUID.randomUUID();
        return uuid.toString().replaceAll("-", "");
    }
}
