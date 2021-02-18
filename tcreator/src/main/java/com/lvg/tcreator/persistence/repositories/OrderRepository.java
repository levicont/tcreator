package com.lvg.tcreator.persistence.repositories;

import com.lvg.tcreator.models.NdtMethod;
import com.lvg.tcreator.persistence.models.OrderDB;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface OrderRepository extends JpaRepository<OrderDB,Long>{
    OrderDB findByNumberAndDateAndNdtMethod(String orderNumber, LocalDate orderDate, NdtMethod ndtMethod);
}
