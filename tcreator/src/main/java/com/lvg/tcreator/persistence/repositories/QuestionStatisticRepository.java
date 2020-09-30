package com.lvg.tcreator.persistence.repositories;

import com.lvg.tcreator.persistence.models.QuestionStatisticDB;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionStatisticRepository extends JpaRepository<QuestionStatisticDB, Long> {
    List<QuestionStatisticDB> findByOrderId(Long orderId);
}
