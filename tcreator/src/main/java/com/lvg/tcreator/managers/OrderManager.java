package com.lvg.tcreator.managers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.lvg.tcreator.models.NdtMethod;
import com.lvg.tcreator.models.OrderDTO;

public class OrderManager {
	private static final String DEFAULT_NDT_METHOD = NdtMethod.RT.toString();
	private static final String DEFAULT_NUMBER_SEPARATOR = "/";
	public static OrderDTO getDefaultOrder(){
		return getDefaultOrder(DEFAULT_NDT_METHOD);
	}
	
	public static OrderDTO getDefaultOrder(String ndtMethod){
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setDate(LocalDate.now());
		orderDTO.setNdtMethod(NdtMethod.valueOf(ndtMethod));
		orderDTO.setNumber(getDefaultOrderNumber());
		orderDTO.setVariantCount(1);
		orderDTO.setIsTotalTest(true);
		orderDTO.setIsSpecTest(true);
		return orderDTO;
	}
	
	public static String getDefaultOrderNumber(){
		StringBuilder result  = new StringBuilder();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		Month month = LocalDate.now().getMonth();

		if(month.getValue() < Month.OCTOBER.getValue())
			result.append("0").append(month.getValue());
		else
			result.append(month.getValue());
		result.append(DEFAULT_NUMBER_SEPARATOR).append("01");
		return result.toString();
	}

	public static OrderDTO getOrderFromExcelFile(String path)throws IOException {
	 throw new UnsupportedOperationException();
	}
	
}
