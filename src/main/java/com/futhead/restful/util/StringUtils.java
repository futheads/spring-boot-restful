package com.futhead.restful.util;

import java.io.UnsupportedEncodingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by futhead on 2017-8-9.
 */
public class StringUtils {

	/**
	 * 空字符串 － ""。
	 */
	public static final String EMPTY_STRING = "";

	/**
	 * 判断字符串对象是否为空，包括空（null）和空字符串（""）。
	 *
	 * @param s
	 *            String - 待判断字符串
	 * @param trimmed
	 *            boolean - 判断时是否删除前后空格（trim）
	 * @return boolean - 返回是否为空，true表示空，false表示非空
	 */
	public static boolean isEmpty(String s, boolean trimmed) {
		if (trimmed)
			return (s == null || EMPTY_STRING.equals(s.trim()));
		else
			return (s == null || EMPTY_STRING.equals(s));
	}

	/**
	 * 判断字符串对象是否为空，包括空（null）和空字符串（""）。
	 *
	 * @param s
	 *            String - 待判断字符串
	 * @return boolean - 返回是否为空，true表示空，false表示非空
	 */
	public static boolean isEmpty(String s) {
		return isEmpty(s, true);
	}

}
