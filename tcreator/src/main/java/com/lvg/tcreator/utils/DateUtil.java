package com.lvg.tcreator.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {
	private static final String DEFAULT_DATE_FORMAT = "dd.MM.yyyy";
	
	private static final SimpleDateFormat smdFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
	
	public static String formatDate(Date date){
		smdFormat.applyPattern(DEFAULT_DATE_FORMAT);
		return smdFormat.format(date);
	}

	public static String formatDate(LocalDate date){
		return date.format(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT));
	}
	
	public static LocalDate parseDate(String value){
		return LocalDate.parse(value);
	}
}
