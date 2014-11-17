package com.forerunner.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * URL 处理工具包
 * @author GQ
 */
public class UrlUtils {
	/**
	 * 附加参数到URL
	 */
	public static String appendParameter(String url, String key, String value) {
		if (StringUtils.isBlank(key) || StringUtils.isBlank(value))
			return url;

		String args = key + "=" + value;

		url = url.indexOf("?") == -1 ? url + "?" + args : url + "&" + args;

		return url;
	}
}
