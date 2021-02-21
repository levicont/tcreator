package com.lvg.tcreator.persistence.services.impl;

import com.lvg.tcreator.exceptions.TCreatorException;
import com.lvg.tcreator.persistence.models.OrderDB;
import com.lvg.tcreator.persistence.models.QuestionStatisticDB;
import com.lvg.tcreator.persistence.repositories.QuestionStatisticRepository;
import com.lvg.tcreator.persistence.services.QuestionStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuestionStatisticServiceDBImpl implements QuestionStatisticService {

    @Autowired
    QuestionStatisticRepository repository;

    @Override
    public List<QuestionStatisticDB> findByOrder(OrderDB order) {
        return repository.findByOrderId(order.getId());
    }

    @Override
    public void save(QuestionStatisticDB record) {
        repository.save(record);

    }

    @Override
    public void delete(QuestionStatisticDB record) {
        repository.delete(record);
    }

    @Override
    public QuestionStatisticDB find(Long id) {
        try{
            return repository.getOne(id);
        }
        catch (JpaObjectRetrievalFailureException ex){
            throw new TCreatorException(UNABLE_TO_FIND_RECORD_WITH_ID+id);
        }
    }

    @Override
    public List<QuestionStatisticDB> findAll() {
        return repository.findAll();
    }

    @Override
    public long count() {
        return repository.count();
    }
}
