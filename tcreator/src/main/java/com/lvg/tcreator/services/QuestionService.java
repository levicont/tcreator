package com.lvg.tcreator.services;

import java.util.List;
import java.util.Set;

import com.lvg.tcreator.models.NdtMethod;
import com.lvg.tcreator.models.Question;
import com.lvg.tcreator.models.QuestionDTO;
import com.lvg.tcreator.models.TestTypes;
import com.lvg.tcreator.persistence.models.QuestionDB;

public interface QuestionService  {
	Set<Question> getRandomQuestionFromList(List<Question> questions, int count);
	List<QuestionDTO> getAllQuestionFromFileSource(NdtMethod ndtMethod, TestTypes testType);
	List<QuestionDTO> findByNdtMethodFromFileSource(NdtMethod ndtMethod);
	QuestionDTO getQuestionDtoFromDbEntity(QuestionDB questionDB);
	void storeAllQuestionsInDB();
}
