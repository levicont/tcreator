package com.lvg.tcreator.config;

public interface R {
	interface ExcelProps{
		String EXCEL_SHEET_QUESTION_NAME = "Материалы";
		String EXCEL_SHEET_ORDER_NAME = "Распоряжение";

	}

	interface GlobalAttributes{
		String BODY_TEMPLATE_ATTRIBUTE = "body_content";
		String ERROR_MESSAGE_ATTRIBUTE = "errorMessage";
	}
	interface OrderProps{
		String DEFAULT_DATE_FORMAT="dd.MM.yyyy";
		int MIN_VARIANT_COUNT_VALUE = 1;
		int MAX_VARIANT_COUNT_VALUE = 3;
		String INVALID_VARIANT_COUNT_MESSAGE = "Количество вариантов должно быть положительным числом не более "+
		MAX_VARIANT_COUNT_VALUE;
		
		int MAX_NUMBER_SIZE_VALUE = 10;
		int MIN_NUMBER_SIZE_VALUE = 1;
		String INVALID_NUMBER_SIZE_MESSAGE = "Размер номера не должен превышать 10 символов";
		
		String INVALID_DATE_MESSAGE = "Неверный формат даты: дд.мм.гггг";
	}

	interface ExamProperties{
		int TOTAL_TEST_UT_RT_QUESTIONS_COUNT = 40;
		int TOTAL_TEST_MT_VT_PT_QUESTIONS_COUNT = 30;
		int SPEC_TEST_QUESTIONS_COUNT = 12;
		int SPEC_TEST_ONE_SECTOR_QUESTIONS_COUNT = 8;
		int SPEC_TEST_TWO_SECTOR_QUESTIONS_COUNT = 9;
		int SPEC_TEST_THREE_SECTOR_QUESTIONS_COUNT = 9;
	}
	
	interface Exceptions {
		String ERROR_MSG_PAGE_INACCESSABLE = "Данная страница недоступна";
		String ERROR_MSG_404 = "HTTP 404: Данная страница недоступна";
		String ERROR_MSG_500 = "HTTP 500: Внутренняя ошибка сервера";
	}
}
