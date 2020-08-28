package com.lvg.tcreator.services;

import java.util.List;
import java.util.Set;

import com.lvg.tcreator.models.NdtMethod;
import com.lvg.tcreator.models.Question;
import com.lvg.tcreator.models.TestTypes;

public interface QuestionService  {
	Set<Question> getRandomQuestionFromList(List<Question> questions, int count);
	List<Question> getAllQuestion(NdtMethod ndtMethod, TestTypes testType);

}
