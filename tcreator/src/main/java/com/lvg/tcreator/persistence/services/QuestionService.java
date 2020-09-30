package com.lvg.tcreator.persistence.services;


import com.lvg.tcreator.models.NdtMethod;
import com.lvg.tcreator.models.TestTypes;
import com.lvg.tcreator.persistence.models.QuestionDB;

import java.util.List;

public interface QuestionService extends GenericServiceDB<QuestionDB>{
    QuestionDB findByNumberTestTypesNdtMethod(Integer number, TestTypes testTypes, NdtMethod ndtMethod);
    List<QuestionDB> findByNdtMethod(NdtMethod ndtMethod);
}
