package com.lvg.tcreator.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	private static final String DEFAULT_DATE_FORMAT = "dd.MM.yyyy";
	
	private static final SimpleDateFormat smdFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
	
	public static String formatDate(Date date){
		String result = smdFormat.format(date);
		return result;
	}
}
