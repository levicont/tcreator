package com.lvg.tcreator.factories;

import com.lvg.tcreator.config.DataSourceType;
import com.lvg.tcreator.services.QuestionService;
import com.lvg.tcreator.services.impl.QuestionServiceImpl;

public class ServiceFactory {	
	
	
	private ServiceFactory(){}
	
	public static QuestionService getQuestionService(DataSourceType dataSourceType){
		return new QuestionServiceImpl();
	}
}
