package com.lvg.tcreator.persistence.services;

import com.lvg.tcreator.persistence.models.ModelDB;

import java.util.List;

public interface GenericServiceDB<T extends ModelDB> {
    String UNABLE_TO_FIND_RECORD_WITH_ID = "Unable to find record with id: ";

    void save(T record);
    void delete(T record);
    T find(Long id);
    List<T> findAll();
    long count();

}
