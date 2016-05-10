package com.lvg.tcreator.test.services.impl.ods;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.junit.Test;

import com.lvg.tcreator.config.DataSourceType;
import com.lvg.tcreator.factories.ServiceFactory;
import com.lvg.tcreator.models.NdtMethod;
import com.lvg.tcreator.models.Question;
import com.lvg.tcreator.models.TestTypes;
import com.lvg.tcreator.services.QuestionService;

public class QuestionServiceImplOdsTest {
	private static org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();

	@Test
	public void getAllQuestionFromOdsTest(){
		QuestionService qService = ServiceFactory.getQuestionService(DataSourceType.ODS);
		for(NdtMethod method : NdtMethod.values()){
			for(TestTypes testType : TestTypes.values()){
				LOGGER.debug("Method: "+method+" TestType:"+testType);
				List<Question> list = qService.getAllQuestion(method, testType);
				assertNotNull(list);
				assertFalse(list.isEmpty());
				Question firstQuestion = list.get(0);
				assertEquals(firstQuestion.getNumber(), 1);
			}
		}
			
	}
	
	@Test
	public void getRandomQuestionFromList(){
		QuestionService qService = ServiceFactory.getQuestionService(DataSourceType.ODS);
		List<Question> list = qService.getAllQuestion(NdtMethod.MT, TestTypes.TOTAL_TEST);
		Set<Question> set = qService.getRandomQuestionFromList(list, 30);
		assertNotNull(set);		
		assertEquals(30, set.size());		
	}

}
