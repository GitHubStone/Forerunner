package com.forerunner.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理工具包
 * @author GQ
 */
public class DateUtils {

	/**
	 * 获取当前日期
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Date> T today(Class<T> clazz) {
		if (clazz.getName().equals(Date.class.getName()))
			return (T) new Date();

		if (clazz.getName().equals(java.sql.Date.class.getName()))
			return (T) new java.sql.Date(System.currentTimeMillis());

		return null;
	}

	public static Date today() {
		return today(Date.class);
	}

	public static int getYearOfDate(Date date) {
		Calendar gc = prepare(date);
		return gc.get(Calendar.YEAR);
	}

	public static int getMonthOfDate(Date date) {
		Calendar gc = prepare(date);
		return gc.get(Calendar.MONTH);
	}

	public static int getDayOfDate(Date date) {
		Calendar gc = prepare(date);
		return gc.get(Calendar.DAY_OF_MONTH);
	}

	public static Date addYear(Date date, int year) {
		Calendar gc = prepare(date);
		gc.add(Calendar.YEAR, year);
		return gc.getTime();
	}

	public static Date addMonth(Date date, int month) {
		Calendar gc = prepare(date);
		gc.add(Calendar.MONTH, month);
		return gc.getTime();
	}

	public static Date addDay(Date date, int day) {
		Calendar gc = prepare(date);
		gc.add(Calendar.DAY_OF_MONTH, day);
		return gc.getTime();
	}

	public static Date mergeDate(Date day, Date time) {
		Calendar result = Calendar.getInstance();

		result.setTime(time);
		Calendar dayHelper = Calendar.getInstance();
		dayHelper.setTime(day);
		result.set(dayHelper.get(Calendar.YEAR), dayHelper.get(Calendar.MONTH), dayHelper.get(Calendar.DATE));

		return result.getTime();
	}

	private static Calendar prepare(Date date) {
		Calendar gc = Calendar.getInstance();
		gc.setTime(date);
		return gc;
	}

	public static String toString(Date date, String pattern) {
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(date);
	}

	public static String getTimeOnly(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		return dateFormat.format(date);
	}

	public static Date paseTimeOnly(String date) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		return dateFormat.parse(date);
	}

	public static int getDateInfo(Date date, int calendarId) {
		Calendar calendar = prepare(date);
		return calendar.get(calendarId);
	}

	public static Date changeDateInfo(Date date, int calendarid, int value) {
		Calendar calendar = prepare(date);
		calendar.set(calendarid, value);
		return calendar.getTime();
	}

	public static boolean isSameDay(Date date1, Date date2) {
		return org.apache.commons.lang3.time.DateUtils.isSameDay(date1, date2);
	}
}