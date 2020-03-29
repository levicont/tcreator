package com.lvg.tcreator.managers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.lvg.tcreator.models.NdtMethod;
import com.lvg.tcreator.models.Order;

public class OrderManager {
	private static final String DEFAULT_NDT_METHOD = NdtMethod.RT.toString();
	private static final String DEFAULT_NUMBER_SEPARATOR = "/";
	public static Order getDefaultOrder(){		
		return getDefaultOrder(DEFAULT_NDT_METHOD);
	}
	
	public static Order getDefaultOrder(String ndtMethod){
		Order order = new Order();
		order.setDate(LocalDate.now());
		order.setNdtMethod(NdtMethod.valueOf(ndtMethod));
		order.setNumber(getDefaultOrderNumber());
		order.setVariantCount(1);
		order.setIsTotalTest(true);
		order.setIsSpecTest(true);
		return order;
	}
	
	public static String getDefaultOrderNumber(){
		StringBuilder result  = new StringBuilder();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		//int month = calendar.get(Calendar.MONTH)+1;
		Month month = LocalDate.now().getMonth();

		if(month.getValue() < Month.OCTOBER.getValue())
			result.append("0").append(month.getValue());
		else
			result.append(month.getValue());
		result.append(DEFAULT_NUMBER_SEPARATOR).append("01");
		return result.toString();
	}

	public static Order getOrderFromExcelFile(String path)throws IOException {
	 throw new UnsupportedOperationException();
	}
	
}
