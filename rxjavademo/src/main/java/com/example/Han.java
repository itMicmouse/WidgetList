package com.example;

/**
 * Created by yakun on 2016/11/16.
 */

import java.util.regex.Pattern;

/**
 * author: Tong
 * Date: 11/16/13
 * Time: 10:42 PM
 */
public class Han {

    public static void main(String[] args) {
        Han classifier = new Han();
        String[] strings = new String[]{"Hello", "你好", "㐀㐂㐄", "にほんご", "조선어",",.?!@#$%^&()", "，。？！＠＃￥％……＆（）"};

        for (int i = 0; i < strings.length; i++) {
            String str = strings[i];
            System.out.println("原字符串为：" + str);
            char[] chars = str.toCharArray();

            if (i < 5) {
                System.out.println("使用0x4E00-0x9FCC范围判断字符是否为汉字：");
                for (char aChar : chars) {
                    System.out.println(aChar + ":" + (classifier.isChineseByRange(String.valueOf(aChar)) ? "YES" : "NO"));
                }

                System.out.println("使用UnicodeBlock方法判断字符是否为汉字：");
                for (char aChar : chars) {
                    System.out.println(aChar + ":" + (classifier.isChineseByBlock(aChar) ? "YES" : "NO"));
                }

                System.out.println("使用UnicodeScript方法判断字符是否为汉字：");
                for (char aChar : chars) {
                    System.out.println(aChar + ":" + (classifier.isChineseByScript(aChar) ? "YES" : "NO"));
                }
            } else {
                System.out.println("判断是否为中文符号：");
                for (char aChar : chars) {
                    System.out.println(aChar + ":" + (classifier.isChinesePunctuation(aChar) ? "YES" : "NO"));
                }

            }
        }
    }

    //使用UnicodeBlock方法判断
    public boolean isChineseByBlock(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_C
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_D
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT) {
            return true;
        } else {
            return false;
        }
    }


    //使用UnicodeScript方法判断
    public boolean isChineseByScript(char c) {
        Character.UnicodeScript sc = Character.UnicodeScript.of(c);
        if (sc == Character.UnicodeScript.HAN) {
            return true;
        }
        return false;
    }

    // 根据UnicodeBlock方法判断中文标点符号
    public boolean isChinesePunctuation(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_FORMS
                || ub == Character.UnicodeBlock.VERTICAL_FORMS) {
            return true;
        } else {
            return false;
        }
    }

    // 使用Unicode编码范围来判断汉字；这个方法不准确,因为还有很多汉字不在这个范围之内
    public boolean isChineseByRange(String str) {
        if (str == null) {
            return false;
        }
        Pattern pattern = Pattern.compile("[\\u4E00-\\u9FCC]+");
        return pattern.matcher(str.trim()).find();
    }

}