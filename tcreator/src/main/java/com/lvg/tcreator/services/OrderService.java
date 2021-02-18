package com.lvg.tcreator.services;

import com.lvg.tcreator.models.NdtMethod;
import com.lvg.tcreator.models.OrderDTO;
import com.lvg.tcreator.persistence.models.OrderDB;

import java.time.LocalDate;

public interface OrderService {
    OrderDTO loadFromFile(byte[] file);
    OrderDTO findByNumberAndDateAndNdtMethod(String orderNumber, LocalDate orderDate, NdtMethod ndtMethod);
    OrderDTO getDtoFromDbEntity(OrderDB orderDB);
    OrderDTO getDefaultOrder();
    OrderDTO getDefaultOrder(NdtMethod ndtMethod);
    String getDefaultOrderNumber();


}
