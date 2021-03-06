package com.lvg.tcreator.managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lvg.tcreator.config.DataSourceType;
import com.lvg.tcreator.factories.ServiceFactory;
import com.lvg.tcreator.models.NdtMethod;
import com.lvg.tcreator.models.Order;
import com.lvg.tcreator.models.Question;
import com.lvg.tcreator.models.Test;
import com.lvg.tcreator.models.TestTypes;
import com.lvg.tcreator.services.QuestionService;

public class TestManager {
	private static final int TOTAL_TEST_UT_RT_QUESTIONS_COUNT = 40;
	private static final int TOTAL_TEST_MT_VT_PT_QUESTIONS_COUNT = 30;
	private static final int SPEC_TEST_QUESTIONS_COUNT = 12;
	private static final int SPEC_TEST_ONE_SECTOR_QUESTIONS_COUNT = 8;
	private static final int SPEC_TEST_TWO_SECTOR_QUESTIONS_COUNT = 9;
	private static final int SPEC_TEST_THREE_SECTOR_QUESTIONS_COUNT = 9;

	private static final String TOTAL_TEST_TITLE = "Общий экзамен";
	private static final String SPEC_TEST_TITLE = "Специальный экзамен общая часть";
	private static final String SPEC_TEST_6_SECTOR_TITLE = "Специальный экзамен 6-й сектор";
	private static final String SPEC_TEST_7_SECTOR_TITLE = "Специальный экзамен 7-й сектор";
	private static final String SPEC_TEST_8_SECTOR_TITLE = "Специальный экзамен 8-й сектор";
	
	private Map<TestTypes, List<Question>> allQuestionsMap = new HashMap<TestTypes, List<Question>>();

	private Order order;
	private QuestionService qService;
	

	
	public TestManager(Order order) {
		this.order = order;
		qService = ServiceFactory.getQuestionService(DataSourceType.ODS);
		initAllQuestionsMap(order.getNdtMethod());
		
	}

	private void initAllQuestionsMap(NdtMethod method) {	
		allQuestionsMap.put(TestTypes.TOTAL_TEST, qService.getAllQuestion(method, TestTypes.TOTAL_TEST));
		allQuestionsMap.put(TestTypes.SPEC_TEST, qService.getAllQuestion(method, TestTypes.SPEC_TEST));
		allQuestionsMap.put(TestTypes.SPEC_6_SECTOR_TEST,
				qService.getAllQuestion(method, TestTypes.SPEC_6_SECTOR_TEST));
		allQuestionsMap.put(TestTypes.SPEC_7_SECTOR_TEST,
				qService.getAllQuestion(method, TestTypes.SPEC_7_SECTOR_TEST));
		allQuestionsMap.put(TestTypes.SPEC_8_SECTOR_TEST,
				qService.getAllQuestion(method, TestTypes.SPEC_8_SECTOR_TEST));

	}	

	public List<Test> createTestList() {
		List<Test> tests = new ArrayList<Test>();
		int variantCount = order.getVariantCount();
		if (order.getIsTotalTest())
			tests.addAll(getSameTestsWithDiferentVariants(TestTypes.TOTAL_TEST, variantCount));
		if (order.getIsSpecTest())
			tests.addAll(getSameTestsWithDiferentVariants(TestTypes.SPEC_TEST, variantCount));
		if (order.getIs6sector())
			tests.addAll(getSameTestsWithDiferentVariants(TestTypes.SPEC_6_SECTOR_TEST, variantCount));
		if (order.getIs7sector())
			tests.addAll(getSameTestsWithDiferentVariants(TestTypes.SPEC_7_SECTOR_TEST, variantCount));
		if (order.getIs8sector())
			tests.addAll(getSameTestsWithDiferentVariants(TestTypes.SPEC_8_SECTOR_TEST, variantCount));

		return tests;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}	

	private List<Test> getSameTestsWithDiferentVariants(TestTypes testType, int variantCount) {
		List<Test> tests = new ArrayList<Test>();
		for (int i = 1; i <= variantCount; i++) {
			Test t = getTestFromExcel(testType);
			t.setVariantNumber(i);
			tests.add(t);
		}
		return tests;
	}
	
	private Test getTestFromExcel(TestTypes testType) {
		Test test = new Test();
		Set<Question> questions = qService.getRandomQuestionFromList(allQuestionsMap.get(testType),
				calculateQuestionsCount(testType));
		test.setQuestions(questions);
		test.setId(generateTestId());
		test.setSize(questions.size());
		test.setTitle(getTestTitle(testType));
		test.setOrder(order);
		return test;
	}

	private long generateTestId() {
		return System.currentTimeMillis();
	}

	
	private int calculateQuestionsCount(TestTypes testType) {
		int sectorsCount = getSectorsCount();

		if (testType == TestTypes.TOTAL_TEST) {
			if (order.getNdtMethod() == NdtMethod.RT || order.getNdtMethod() == NdtMethod.UT)
				return TOTAL_TEST_UT_RT_QUESTIONS_COUNT;
			else
				return TOTAL_TEST_MT_VT_PT_QUESTIONS_COUNT;
		} else if (testType == TestTypes.SPEC_TEST) {
			return SPEC_TEST_QUESTIONS_COUNT;
		} else if (sectorsCount == 3) {
			return SPEC_TEST_THREE_SECTOR_QUESTIONS_COUNT;
		} else if (sectorsCount == 2) {
			return SPEC_TEST_TWO_SECTOR_QUESTIONS_COUNT;
		} else
			return SPEC_TEST_ONE_SECTOR_QUESTIONS_COUNT;
	}

	private int getSectorsCount() {
		if (order.getIs6sector() && order.getIs7sector() && order.getIs8sector())
			return 3;
		else if (order.getIs6sector() && order.getIs7sector())
			return 2;
		else if (order.getIs6sector() && order.getIs8sector())
			return 2;
		else if (order.getIs7sector() && order.getIs8sector())
			return 2;
		else
			return 1;

	}

	private String getTestTitle(TestTypes testType) {
		if (testType == TestTypes.TOTAL_TEST)
			return TOTAL_TEST_TITLE;
		if (testType == TestTypes.SPEC_TEST)
			return SPEC_TEST_TITLE;
		if (testType == TestTypes.SPEC_6_SECTOR_TEST)
			return SPEC_TEST_6_SECTOR_TITLE;
		if (testType == TestTypes.SPEC_7_SECTOR_TEST)
			return SPEC_TEST_7_SECTOR_TITLE;
		if (testType == TestTypes.SPEC_8_SECTOR_TEST)
			return SPEC_TEST_8_SECTOR_TITLE;

		throw new RuntimeException("Ivalid test title");
	}

}
