package com.lvg.tcreator.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import com.lvg.tcreator.models.Question;
import com.lvg.tcreator.models.TestTypes;
import com.lvg.tcreator.services.QuestionService;

public abstract class QuestionServiceImpl implements QuestionService{	
	
	protected static Map<TestTypes, Integer> srcSheetMap = new HashMap<>();
	
	static {
		srcSheetMap.put(TestTypes.TOTAL_TEST, 0);
		srcSheetMap.put(TestTypes.SPEC_TEST, 1);
		srcSheetMap.put(TestTypes.SPEC_6_SECTOR_TEST, 2);
		srcSheetMap.put(TestTypes.SPEC_7_SECTOR_TEST, 3);
		srcSheetMap.put(TestTypes.SPEC_8_SECTOR_TEST, 4);
	}
	
	
	public Set<Question> getRandomQuestionFromList(List<Question> questList, int countQuestions) {
		if (questList.size() < countQuestions)
			throw new IllegalArgumentException("Question base size less than count of questions.");
		Set<Question> result = new TreeSet<>();

		while (result.size() < countQuestions) {
			int index = generateIndex(questList.size());
			result.add(questList.get(index));
		}
		return result;
	}
	
	protected int generateIndex(int max) {
		Random random = new Random();
		return random.nextInt(max);
	}
	
	
	protected int getNumberQuestionFromFirstRow(String firstRow) {
		String num = "-1";
		try {
			num = firstRow.substring(0, firstRow.indexOf(". "));
		} catch (StringIndexOutOfBoundsException ex) {
			System.out.println("INVALID QUESTION: " + ex.getMessage());
			System.out.println("firstRow:  " + firstRow);
			System.out.println("IndexOf('. '): " + firstRow.indexOf(". "));
			System.out.println("*****************");
			throw new RuntimeException("INVALID STRING PARAMETER");
		}
		return Integer.parseInt(num);
	}
}
