package com.lvg.tcreator.models;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ExamDTO {

    private TestTypes testType;
    private NdtMethod ndtMethod;
    private final List<ExamTicketDTO> examTicketDTOList = new ArrayList<>();

    public ExamDTO(TestTypes testType, NdtMethod ndtMethod) {
        this.testType = testType;
        this.ndtMethod = ndtMethod;
    }

    public void addExamTicketDTO(ExamTicketDTO examTicketDTO){
        examTicketDTOList.add(examTicketDTO);
        examTicketDTO.setTicketNumber(examTicketDTOList.size());
    }

    public void removeExamTicketDTO(ExamTicketDTO examTicketDTO){
        examTicketDTOList.remove(examTicketDTO);
        AtomicInteger index = new AtomicInteger(1);
        examTicketDTOList.forEach(examTicket ->{
            examTicket.setTicketNumber(index.getAndIncrement());
        });
    }

    public List<ExamTicketDTO> getExamTicketDTOList() {
        return examTicketDTOList;
    }

    public TestTypes getTestType() {
        return testType;
    }

    public void setTestType(TestTypes testType) {
        this.testType = testType;
    }

    public NdtMethod getNdtMethod() {
        return ndtMethod;
    }

    public void setNdtMethod(NdtMethod ndtMethod) {
        this.ndtMethod = ndtMethod;
    }
}
