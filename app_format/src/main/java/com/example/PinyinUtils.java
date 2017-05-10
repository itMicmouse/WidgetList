package com.hospital.utils;

import android.text.TextUtils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinyinUtils {

	/**
	 * 根据传入的字符串(包含汉字),得到拼音 杨亚坤 -> YANGYAKUN 张 涵*& -> ZHANGHAN 流星f5 -> 流星
	 * 
	 * @param str
	 *            字符串
	 * @return
	 */
	public static String getPinyin(String str) {

		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

		StringBuilder sb = new StringBuilder();

		char[] charArray = str.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			char c = charArray[i];
			// 如果是空格, 跳过
			if (Character.isWhitespace(c)) {
				continue;
			}
			
			if (c >= -127 && c < 128) {
				// 肯定不是汉字
				sb.append("#");
			}else {
				String s = "";
				try {
					// 通过char得到拼音集合. 单 -> dan, shan
					s = PinyinHelper.toHanyuPinyinStringArray(c, format)[0];
					sb.append(s);
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
					sb.append(s);
				}
			}
		}

		return sb.toString();
	}

	/**
	 * 获取拼音简拼
	 * 
	 * @param str
	 * @return
	 */
	public static String getPinyinjp(String str) {
		if(TextUtils.isEmpty(str)){
			return "";
		}

		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

		StringBuilder sb = new StringBuilder();

		char[] charArray = str.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			char c = charArray[i];
			// 如果是空格, 跳过
			if (Character.isWhitespace(c)) {
				continue;
			}
			if (c >= -127 && c < 128) {
				if(c>=65&&c<=90){
					sb.append(c);
				}else if(c>=97&&c<=122){
					sb.append(c);
				}else if(c>=48&&c<=57){
					sb.append(c);
				}else
				// 肯定不是汉字
				sb.append("#");
			} else {
				String s = "";
				try {
					// 通过char得到拼音集合. 单 -> dan, shan
					
					if(PinyinHelper.toHanyuPinyinStringArray(c, format)!=null&&PinyinHelper.toHanyuPinyinStringArray(c, format).length>0&&PinyinHelper.toHanyuPinyinStringArray(c, format)[0]!=null){
						s = PinyinHelper.toHanyuPinyinStringArray(c, format)[0];
						sb.append(s.charAt(0));
					}else{
						sb.append(c);
					}
//					
//					if (PinyinHelper.toHanyuPinyinStringArray(c, format)!=null) {
//						s = PinyinHelper.toHanyuPinyinStringArray(c, format)[0];
//						sb.append(s.charAt(0));
//					}else {
//						sb.append("*");
//					}
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
					sb.append(s);
				}
			}
		}

		return sb.toString().toUpperCase();
	}

	/**
	 * 获取拼音简拼 没有#
	 *
	 * @param str
	 * @return
	 */
	public static String getPinyinjp5(String str) {
		if(TextUtils.isEmpty(str)){
			return "";
		}

		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

		StringBuilder sb = new StringBuilder();

		char[] charArray = str.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			char c = charArray[i];
			// 如果是空格, 跳过
			if (Character.isWhitespace(c)) {
				continue;
			}
			if (c >= -127 && c < 128) {
				if(c>=65&&c<=90){
					sb.append(c);
				}else if(c>=97&&c<=122){
					sb.append(c);
				}else if(c>=48&&c<=57){
					sb.append(c);
				}else {
					// 肯定不是汉字
					sb.append("#");
				}
			} else {
				String s = "";
				try {
					// 通过char得到拼音集合. 单 -> dan, shan

					if(PinyinHelper.toHanyuPinyinStringArray(c, format)!=null&&PinyinHelper.toHanyuPinyinStringArray(c, format).length>0&&PinyinHelper.toHanyuPinyinStringArray(c, format)[0]!=null){
						s = PinyinHelper.toHanyuPinyinStringArray(c, format)[0];
						sb.append(s.charAt(0));
					}else{
						sb.append(c);
					}
//
//					if (PinyinHelper.toHanyuPinyinStringArray(c, format)!=null) {
//						s = PinyinHelper.toHanyuPinyinStringArray(c, format)[0];
//						sb.append(s.charAt(0));
//					}else {
//						sb.append("*");
//					}
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
					sb.append(s);
				}
			}
		}

		return sb.toString().toUpperCase();
	}
	
	public static boolean isHan(String string){
		
		char[] charArray = string.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			char c = charArray[i];
			if (Character.isWhitespace(c)) {
				return false;
			}
//			if (c >= -127 && c < 128) {
//				if(c>=65&&c<=90){
//					continue;
//				}else if(c>=97&&c<=122)
//					continue;
//				else if(c>=48&&c<=57)
//					continue;
//				return false;
//			}
			if(isChinese(c)){
				return false;
			}else {
				continue;
			}
		}
		return true;
	}

	public static boolean isHanPrint(String string){

		char[] charArray = string.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			char c = charArray[i];
			if (Character.isWhitespace(c)) {
				return false;
			}
			if (c >= -127 && c < 128) {
				if(c>=65&&c<=90){
					continue;
				}else if(c>=97&&c<=122)
					continue;
				else if(c>=48&&c<=57)
					continue;
				return false;
			}
		}
		return true;
	}

	// 根据Unicode编码完美的判断中文汉字和符号
	public static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
			return true;
		}
		return false;
	}

	/**
	 * 2~10个汉字数字或字母
	 * @param charArray 要判定的数据
	 * @return
     */
	public static boolean isPatientName(String charArray){
		if(TextUtils.isEmpty(charArray)){
			charArray = "";
		}
		if(charArray.length()<2 || charArray.length()>10){
			return false;
		}
		for (int i = 0; i < charArray.length(); i++) {
			char c = charArray.charAt(i);
			if(isChinese(c)){
				continue;
			}else if(Character.isUpperCase(c)||Character.isLowerCase(c)){//大写字母或小写字母
				continue;
			} else if(Character.isDigit(c)){//数字
				continue;
			}else {
				return false;
			}
		}
		return true;
	}

}
