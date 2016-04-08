package com.lvg.tcreator.factories;

import com.lvg.tcreator.config.DataSourceType;
import com.lvg.tcreator.services.QuestionService;
import com.lvg.tcreator.services.impl.excel.QuestionServiceImplExcel;
import com.lvg.tcreator.services.impl.ods.QuestionServiceImplODS;

public class ServiceFactory {	
	
	
	private ServiceFactory(){}
	
	public static QuestionService getQuestionService(DataSourceType dataSourceType){
		if(dataSourceType == DataSourceType.EXCEL){
			return new QuestionServiceImplExcel();
		}else
			return new QuestionServiceImplODS();
	}
}
