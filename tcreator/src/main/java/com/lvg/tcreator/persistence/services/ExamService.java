package com.lvg.tcreator.persistence.services;

import com.lvg.tcreator.persistence.models.ExamDB;
import com.lvg.tcreator.persistence.models.OrderDB;

import java.util.List;

public interface ExamService extends GenericServiceDB<ExamDB> {

    List<ExamDB> findByOrder(OrderDB order);
}
