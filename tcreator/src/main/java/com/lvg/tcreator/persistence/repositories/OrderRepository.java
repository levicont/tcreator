package com.lvg.tcreator.persistence.repositories;

import com.lvg.tcreator.persistence.models.OrderDB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderDB,Long>{
}
