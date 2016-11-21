package com.example;

import java.util.ArrayList;

/**
 * Created by yakun on 2016/11/14.
 */

public class Eq {
    public static void main(String[] args) {
        ArrayList domain = new ArrayList();
        Domain domain1 ;
        domain1 = new Domain();
        Domain abcdef = new Domain();
        abcdef.setId(1+"");
        abcdef.setName(""+1);
        abcdef.setAge("2");
        abcdef.setSex("3");
        for (int i = 0; i < 5; i++) {
            domain1 = new Domain();
            domain1.setId(i+"");
            domain1.setName("亚坤"+i);
            domain1.setAge(i+"2");
            domain1.setSex("1");
            domain.add(domain1);
        }
        if(domain.contains(abcdef)){
            System.out.println("123456");
        }
    }
}
