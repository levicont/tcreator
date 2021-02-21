package com.lvg.tcreator.persistence.services.impl;

import com.lvg.tcreator.exceptions.TCreatorException;
import com.lvg.tcreator.persistence.models.ExamDB;
import com.lvg.tcreator.persistence.models.ExamTicketDB;
import com.lvg.tcreator.persistence.models.OrderDB;
import com.lvg.tcreator.persistence.repositories.ExamRepository;
import com.lvg.tcreator.persistence.services.ExamService;
import com.lvg.tcreator.persistence.services.ExamTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Component
public class ExamServiceDBImpl implements ExamService{

    @Autowired
    private ExamRepository repository;
    @Autowired
    private ExamTicketService examTicketService;

    @Override
    public void save(ExamDB record) {
        Set<ExamTicketDB> unsavedTickets = record.getTickets().stream()
                .filter(t -> t.getId() == null)
                .collect(Collectors.toSet());
        unsavedTickets.forEach(t -> examTicketService.save(t));
        repository.save(record);
    }

    @Override
    public void delete(ExamDB record) {
        repository.delete(record);
    }

    @Override
    public ExamDB find(Long id) {
        try{
            return repository.getOne(id);
        }
        catch (JpaObjectRetrievalFailureException ex){
            throw new TCreatorException(UNABLE_TO_FIND_RECORD_WITH_ID+id);
        }

    }

    @Override
    public List<ExamDB> findByOrder(OrderDB order) {
        return repository.findByOrder(order);
    }

    @Override
    public List<ExamDB> findAll() {
       return repository.findAll();
    }

    @Override
    public long count() {
        return repository.count();
    }
}
