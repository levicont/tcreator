package com.lvg.tcreator.managers;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.lvg.tcreator.models.NdtMethod;
import com.lvg.tcreator.models.Order;

public class OrderManager {
	private static final String DEFAULT_NDT_METHOD = NdtMethod.RT.toString();
	
	public static Order getDefaultOrder(){		
		return getDefaultOrder(DEFAULT_NDT_METHOD);
	}
	
	public static Order getDefaultOrder(String ndtMethod){
		Order order = new Order();
		order.setDate(new Date());
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
		int month = calendar.get(Calendar.MONTH)+1;
		if(month < 10)
			result.append("0"+month);
		else
			result.append(month);
		result.append("\\01");		
		return result.toString();
	}
	
}
