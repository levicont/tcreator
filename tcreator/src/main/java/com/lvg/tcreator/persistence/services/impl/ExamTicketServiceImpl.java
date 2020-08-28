package com.lvg.tcreator.persistence.services.impl;

import com.lvg.tcreator.exceptions.TCreatorException;
import com.lvg.tcreator.persistence.models.ExamTicketDB;
import com.lvg.tcreator.persistence.models.QuestionDB;
import com.lvg.tcreator.persistence.repositories.ExamTicketRepository;
import com.lvg.tcreator.persistence.services.ExamTicketService;
import com.lvg.tcreator.persistence.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ExamTicketServiceImpl implements ExamTicketService {

    @Autowired
    ExamTicketRepository repository;
    @Autowired
    QuestionService questionService;

    @Override
    public void save(ExamTicketDB record) {
        Set<QuestionDB> questions = record.getQuestions().stream()
                .filter(q ->  q.getId() == null)
                .collect(Collectors.toSet());
        questions.forEach(q -> questionService.save(q));

        repository.save(record);
    }

    @Override
    public void delete(ExamTicketDB record) {
        repository.delete(record);
    }

    @Override
    public ExamTicketDB find(Long id) {
        try{
            return repository.getOne(id);
        }catch (JpaObjectRetrievalFailureException ex){
            throw new TCreatorException(UNABLE_TO_FIND_RECORD_WITH_ID+id);
        }
    }

    @Override
    public List<ExamTicketDB> findAll() {
        return repository.findAll();
    }

    @Override
    public long count() {
        return repository.count();
    }
}
