package com.lvg.tcreator.persistence.services.impl;

import com.lvg.tcreator.exceptions.TCreatorException;
import com.lvg.tcreator.models.NdtMethod;
import com.lvg.tcreator.models.TestTypes;
import com.lvg.tcreator.persistence.models.QuestionDB;
import com.lvg.tcreator.persistence.repositories.QuestionRepository;
import com.lvg.tcreator.persistence.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    QuestionRepository repository;

    @Override
    public void save(QuestionDB record) {
        repository.save(record);
    }

    @Override
    public void delete(QuestionDB record) {
        repository.delete(record);
    }

    @Override
    public QuestionDB find(Long id) {
        try {
            return repository.getOne(id);
        }catch (JpaObjectRetrievalFailureException ex){
            throw new TCreatorException(UNABLE_TO_FIND_RECORD_WITH_ID+id);
        }
    }

    @Override
    public List<QuestionDB> findAll() {
        return repository.findAll();
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public QuestionDB findByNumberTestTypesNdtMethod(Integer number, TestTypes testTypes, NdtMethod ndtMethod) {
        return repository.findByQuestionNumberAndTestTypesAndNdtMethod(number, testTypes, ndtMethod);
    }

    @Override
    public List<QuestionDB> findByNdtMethod(NdtMethod ndtMethod) {
        return repository.findByNdtMethod(ndtMethod);
    }
}
