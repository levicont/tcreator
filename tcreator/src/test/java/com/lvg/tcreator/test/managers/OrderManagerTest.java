package com.lvg.tcreator.test.managers;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

import com.lvg.tcreator.managers.OrderManager;
import com.lvg.tcreator.models.Order;

import static org.junit.Assert.*;


public class OrderManagerTest{
	
	@Test
	public void getDefaultOrderTest(){
	
	Order order = OrderManager.getDefaultOrder();
	assertNotNull(order);
	}
	
	@Test
	public void getDefaultOrderNumberTest(){
		Calendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		Order order = OrderManager.getDefaultOrder();
		assertTrue(order.getNumber().endsWith("\\01"));
		
		int month = cal.get(Calendar.MONTH)+1;
		if (month < 10)
			assertEquals("0"+month+"\\01", order.getNumber());
		else
			assertEquals(month+"\\01", order.getNumber());				
	}

}
