package com.lvg.tcreator.persistence.services.impl;

import com.lvg.tcreator.exceptions.TCreatorException;
import com.lvg.tcreator.persistence.models.OrderDB;
import com.lvg.tcreator.persistence.repositories.OrderRepository;
import com.lvg.tcreator.persistence.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderServiceDBImpl implements OrderService {

    @Autowired
    OrderRepository repository;

    @Override
    public void save(OrderDB record) {
        repository.save(record);
    }

    @Override
    public void delete(OrderDB record) {
        repository.delete(record);
    }

    @Override
    public OrderDB find(Long id) {
        try {
            return repository.getOne(id);
        }
        catch (JpaObjectRetrievalFailureException ex){
            throw new TCreatorException(UNABLE_TO_FIND_RECORD_WITH_ID+id);
        }

    }

    @Override
    public List<OrderDB> findAll() {
        return repository.findAll();
    }

    @Override
    public long count() {
        return repository.count();
    }
}
