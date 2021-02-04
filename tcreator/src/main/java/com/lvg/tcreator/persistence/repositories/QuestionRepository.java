package com.lvg.tcreator.persistence.repositories;

import com.lvg.tcreator.models.NdtMethod;
import com.lvg.tcreator.models.TestTypes;
import com.lvg.tcreator.persistence.models.QuestionDB;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface QuestionRepository extends JpaRepository<QuestionDB, Long> {
    QuestionDB findByQuestionNumberAndTestTypesAndNdtMethod(Integer number, TestTypes testTypes, NdtMethod ndtMethod);
    List<QuestionDB> findByNdtMethod(NdtMethod ndtMethod);
    List<QuestionDB> findByNdtMethodAndTestTypes(NdtMethod ndtMethod, TestTypes testTypes);
}
