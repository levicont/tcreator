package com.lvg.tcreator.utils;

import org.apache.logging.log4j.LogManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(DateUtil.class);
	private static final String DEFAULT_DATE_FORMAT = "dd.MM.yyyy";
	
	private static final SimpleDateFormat smdFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
	
	public static String formatDate(Date date){
		smdFormat.applyPattern(DEFAULT_DATE_FORMAT);
		String result = smdFormat.format(date);
		return result;
	}
	
	public static Date parseDate(String value)throws IllegalArgumentException{
		Date result = null;
		try{
			smdFormat.applyPattern("yyyy-MM-dd");
			result = smdFormat.parse(value);
		}catch(ParseException ex){
			LOGGER.error("Not possible to parse source "+value+" into java.util.Date");
			throw new IllegalArgumentException("Not possible to parse source "+value+" into java.util.Date");
		}
		return result;
	}
}
