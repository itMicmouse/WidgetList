package com.example;

import java.util.ArrayList;

/**
 * Created by android on 2017/3/21.
 */

public class Date {
    public static void main(String[] args) {
        ArrayList<String> date1 = new ArrayList<>();
        ArrayList<String> date2 = new ArrayList<>();
        ArrayList<String> date3 = new ArrayList<>();
        ArrayList<String> date4 = new ArrayList<>();
        ArrayList<String> date5 = new ArrayList<>();
        ArrayList<String> date6 = new ArrayList<>();
        ArrayList<String> date7 = new ArrayList<>();
        ArrayList<String> date8 = new ArrayList<>();
        ArrayList<String> date9 = new ArrayList<>();
        ArrayList<String> date10 = new ArrayList<>();
        ArrayList<String> date11= new ArrayList<>();
        ArrayList<String> date12= new ArrayList<>();
        ArrayList<String> date13= new ArrayList<>();
        for (int i = 0; i < 520000; i++) {
            switch (i%13){
                case 0:
                    date1.add(i+"");
                    break;
                case 1:
                    date2.add(i+"");
                    break;
                case 2:
                    date3.add(i+"");
                    break;
                case 3:
                    date4.add(i+"");
                    break;
                case 4:
                    date5.add(i+"");
                    break;
                case 5:
                    date6.add(i+"");
                    break;
                case 6:
                    date7.add(i+"");
                    break;
                case 7:
                    date8.add(i+"");
                    break;
                case 8:
                    date9.add(i+"");
                    break;
                case 9:
                    date10.add(i+"");
                    break;
                case 10:
                    date11.add(i+"");
                    break;
                case 11:
                    date12.add(i+"");
                    break;
                case 12:
                    date13.add(i+"");
                    break;
                default:

            }
        }
        System.out.println(date1);
        System.out.println(date2);
        System.out.println(date3);
        System.out.println(date4);
        System.out.println(date5);
        System.out.println(date6);
        System.out.println(date7);
        System.out.println(date8);
        System.out.println(date9);
        System.out.println(date10);
        System.out.println(date11);
        System.out.println(date12);
        System.out.println(date13);
    }
}
