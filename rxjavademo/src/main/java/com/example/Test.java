package com.example;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by yakun on 2016/9/8.
 */

public class Test {
    String f = "111231";
    String bb = "123";
    public void m1() {
        BigDecimal bg = new BigDecimal(f);
        double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println("m1"+f1);
    }
    /**
     * DecimalFormat转换最简便
     */
    public void m2() {
        DecimalFormat df = new DecimalFormat("#.00");
        System.out.println("m2"+df.format(14));
    }
    /**
     * String.format打印最简便
     */
    public void m3() {
        System.out.println("m3"+String.format("%.2f", bb));
    }
    public void m4() {
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        System.out.println("m4"+nf.format(f));
    }
    public static void main(String[] args) {
        Test f = new Test();
        f.m1();
        f.m2();
        f.m3();
        f.m4();
    }
}
