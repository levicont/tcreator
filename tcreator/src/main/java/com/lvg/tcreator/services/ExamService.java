package com.lvg.tcreator.services;

import com.lvg.tcreator.models.ExamDTO;
import com.lvg.tcreator.models.OrderDTO;
import com.lvg.tcreator.models.TestTypes;
import com.lvg.tcreator.persistence.models.ExamDB;

/**
 * Created by Victor Levchenko (LVG Corp.) on 18.02.2021.
 */
public interface ExamService {
    ExamDTO getExamDtoFromExamDb(ExamDB examDB);
    ExamDTO createExamDTO(OrderDTO orderDTO, TestTypes testTypes, Integer ticketCount);
}
