package com.lvg.tcreator.managers;

import java.util.Date;

import com.lvg.tcreator.models.NdtMethod;
import com.lvg.tcreator.models.Order;

public class OrderManager {
	private static final String DEFAULT_NDT_METHOD = NdtMethod.RT.toString();
	
	public static Order getDefaultOrder(){		
		return getDefaultOrder(DEFAULT_NDT_METHOD);
	}
	
	@SuppressWarnings(value="deprecation")
	public static Order getDefaultOrder(String ndtMethod){
		Order order = new Order();
		order.setDate(new Date());
		order.setNdtMethod(NdtMethod.valueOf(ndtMethod));
		int month = order.getDate().getMonth()+1;	
		StringBuilder number = new StringBuilder();
		if (month < 10)
			number.append("0"+month);
		else
			number.append(month);
		number.append("\\01");		
	
		order.setNumber(number.toString());
		order.setVariantCount(1);
		order.setIsTotalTest(true);
		order.setIsSpecTest(true);
		return order;
	}
}
