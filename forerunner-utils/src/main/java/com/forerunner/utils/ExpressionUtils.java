package com.forerunner.utils;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 表达式处理工具包
 * @author GQ
 *
 */
public class ExpressionUtils {
	/**
	 * 执行动态脚本
	 */
	public static Object executeDynamicScript(Object source, String script) {
		Object result = null;
		try {
			Binding binding = new Binding();
			binding.setVariable("target", source);
			GroovyShell shell = new GroovyShell(binding);

			script = "target." + script;

			result = shell.evaluate(script);
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return result;
	}

	/**
	 * 解析简单的EL表达式
	 */
	public static String matchingSimpleExpression(String contentPattern, Map<String, Object> source) {
		Pattern pattern = Pattern.compile("\\$\\{((\\w|\\.){1,30})\\}");
		Matcher matcher = pattern.matcher(contentPattern);

		StringBuffer stringBuffer = new StringBuffer();
		while (matcher.find()) {
			String field = matcher.group(1);

			Object result = ExpressionUtils.executeDynamicScript(source, field);

			if (result != null)
				field = result.toString();

			matcher.appendReplacement(stringBuffer, field);
		}
		matcher.appendTail(stringBuffer);

		return stringBuffer.toString();
	}

	public static String foreachReplaceExpression(String content, String regex, Callback callback) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(content);

		StringBuffer stringBuffer = new StringBuffer();
		while (matcher.find()) {
			String field = matcher.group(1);

			Object result = callback.process(field);

			if (result != null)
				field = result.toString();

			matcher.appendReplacement(stringBuffer, field);
		}
		matcher.appendTail(stringBuffer);

		return stringBuffer.toString();
	}

	/**
	 * 匹配回调
	 */
	public static abstract class Callback {
		public abstract Object process(String field);
	}
}