package com.lvg.tcreator.persistence.services;

import com.lvg.tcreator.persistence.models.OrderDB;
import com.lvg.tcreator.persistence.models.QuestionStatisticDB;

import java.util.List;

public interface QuestionStatisticService extends GenericServiceDB<QuestionStatisticDB> {
    List<QuestionStatisticDB> findByOrder(OrderDB order);
}
