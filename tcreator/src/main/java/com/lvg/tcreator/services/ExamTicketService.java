package com.lvg.tcreator.services;

import com.lvg.tcreator.models.ExamTicketDTO;
import com.lvg.tcreator.models.NdtMethod;
import com.lvg.tcreator.models.OrderDTO;
import com.lvg.tcreator.models.TestTypes;
import com.lvg.tcreator.persistence.models.ExamTicketDB;

import java.util.List;

/**
 * Created by Victor Levchenko (LVG Corp.) on 18.02.2021.
 */
public interface ExamTicketService {
    ExamTicketDTO getExamTicketDtoFromDbEntity(ExamTicketDB examTicketDB);
    ExamTicketDTO createExamTicketDto(Integer ticketNumber, NdtMethod ndtMethod, TestTypes testTypes, int questionCount);
    List<ExamTicketDTO> getExamTicketsFromOrderDto(OrderDTO orderDTO);
    
}
