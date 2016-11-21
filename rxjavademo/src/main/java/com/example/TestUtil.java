package com.example;

import java.util.UUID;

/**
 * Created by yakun on 2016/9/13.
 */

public class TestUtil {
    public static void main(String[] args) {
        String abc  =  " sdfjl sdj ";
        System.out.println(StringUtilsmine.trim(abc));
        System.out.println(StringUtilsmine.trimToEmpty(abc));
        System.out.println(UUID.randomUUID().toString());
    }
}
