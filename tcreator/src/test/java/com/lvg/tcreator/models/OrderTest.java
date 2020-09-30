package com.lvg.tcreator.models;


import com.lvg.tcreator.exceptions.TCreatorException;
import com.lvg.tcreator.persistence.models.OrderDB;
import com.lvg.tcreator.persistence.services.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class OrderTest {

    @Autowired
    OrderService service;



    @Test
    public void saveOrderTest(){
        OrderDB order = ModelsGenerator.getOrderDB();
        Long id = order.getId();
        assertNull(id);
        service.save(order);
        assertNotNull(order.getId());
    }

    @Test
    public void updateOrderTest(){
        OrderDB order = ModelsGenerator.getOrderDB();
        service.save(order);
        Long id = order.getId();
        LocalDate date = order.getDate();
        String number = order.getNumber();
        NdtMethod method = order.getNdtMethod();

        order.setNumber("03/12");
        order.setNdtMethod(NdtMethod.VT);
        order.setDate(LocalDate.now());
        service.save(order);
        OrderDB updatedOrder = service.find(id);
        assertNotEquals(number, updatedOrder.getNumber());
        assertNotEquals(method, updatedOrder.getNdtMethod());
        assertNotEquals(date, updatedOrder.getDate());
    }

    @Test(expected = TCreatorException.class)
    public void deleteOrderTest(){
        OrderDB order = ModelsGenerator.getOrderDB();
        service.save(order);
        Long id = order.getId();
        assertNotNull(id);
        service.delete(order);
        service.find(id);
    }

    @Test
    public void findAllOrdersTest(){
        int count = service.findAll().size();
        service.save(ModelsGenerator.getOrderDB());
        assertEquals(count+1, service.findAll().size());
    }
}
