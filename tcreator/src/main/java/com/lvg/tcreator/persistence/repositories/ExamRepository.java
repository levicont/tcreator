package com.lvg.tcreator.persistence.repositories;

import com.lvg.tcreator.persistence.models.ExamDB;
import com.lvg.tcreator.persistence.models.OrderDB;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamRepository extends JpaRepository<ExamDB, Long> {
    List<ExamDB> findByOrder(OrderDB order);
}
