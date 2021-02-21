package com.lvg.tcreator.services;

import com.lvg.tcreator.models.NdtMethod;
import com.lvg.tcreator.models.QuestionDTO;
import com.lvg.tcreator.models.TestTypes;
import com.lvg.tcreator.persistence.models.QuestionDB;

import java.util.List;
import java.util.Set;

public interface QuestionService  {
	Set<QuestionDTO> getRandomQuestionFromList(List<QuestionDTO> questions, int count);
	List<QuestionDTO> getAllQuestionFromFileSource(NdtMethod ndtMethod, TestTypes testType);
	List<QuestionDTO> findByNdtMethodFromFileSource(NdtMethod ndtMethod);
	List<QuestionDTO> findByNdtMethodAndTestType(NdtMethod ndtMethod, TestTypes testTypes);
	QuestionDTO getQuestionDtoFromDbEntity(QuestionDB questionDB);
	void storeAllQuestionsInDB();
}
