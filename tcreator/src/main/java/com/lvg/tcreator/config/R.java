package com.lvg.tcreator.config;

public interface R {
	public interface OrderProps{
		public int MIN_VARIANT_COUNT_VALUE = 1;
		public int MAX_VARIANT_COUNT_VALUE = 3;
		public String INVALID_VARIANT_COUNT_MESSAGE = "Количество вариантов должно быть положительным числом не более "+
		MAX_VARIANT_COUNT_VALUE;
		
		public int MAX_NUMBER_SIZE_VALUE = 10;
		public int MIN_NUMBER_SIZE_VALUE = 1;
		public String INVALID_NUMBER_SIZE_MESSAGE = "Размер номера не должен превышать 10 символов";
		
		public String INVALID_DATE_MESSAGE = "Неверный формат даты: дд/мм/гггг";
	}
	
	public interface Exceptions {
		public String ERROR_MSG_PAGE_INACCESSABLE = "Данная страница недоступна";
		public String ERROR_MSG_404 = "HTTP 404: Данная страница недоступна";
		public String ERROR_MSG_500 = "HTTP 500: Внутренняя ошибка сервера";
	}
}
