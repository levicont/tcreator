package com.lvg.tcreator.services.impl;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

import com.lvg.tcreator.converters.QuestionModelConverter;
import com.lvg.tcreator.exceptions.TCreatorException;
import com.lvg.tcreator.models.QuestionDTO;
import com.lvg.tcreator.persistence.models.QuestionDB;
import com.lvg.tcreator.persistence.services.QuestionDBService;
import com.lvg.tcreator.services.QuestionService;
import org.apache.commons.lang3.StringUtils;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

import com.lvg.tcreator.models.NdtMethod;
import com.lvg.tcreator.models.Question;
import com.lvg.tcreator.models.TestTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuestionServiceImpl implements QuestionService {
	private static final String QUESTION_NUMBER_SEPARATOR = ". ";
	private static final String ODS_FILE_SUFFIX = "-II.ods";
	private static final String RESOURCE_PATH = "/ods/";
	private static final int SPREADSHEET_COLUMN_COUNT = 2;

	@Autowired
	private QuestionDBService service;

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

	private int generateIndex(int max) {
		Random random = new Random();
		return random.nextInt(max);
	}


	private int getNumberQuestionFromFirstRow(String firstRow) {
		String num;
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

	@Override
	public void storeAllQuestionsInDB() {
		final List<QuestionDB> allQuestions = new ArrayList<>();
		Arrays.stream(NdtMethod.values())
				.forEach(ndtMethod -> Arrays.stream(TestTypes.values())
						.forEach(testType -> getAllQuestionFromFileSource(ndtMethod, testType)
								.forEach(question -> allQuestions.add(QuestionModelConverter.getQuestionDB(question,ndtMethod,testType)))));
		allQuestions.forEach(q -> service.save(q));
	}

	@Override
	public List<QuestionDTO> findByNdtMethodFromFileSource(NdtMethod ndtMethod) {
		List<QuestionDTO> questions = new ArrayList<>();
		Arrays.stream(TestTypes.values())
				.forEach(testType -> questions.addAll(getAllQuestionFromFileSource(ndtMethod, testType)));
		return questions;
	}

	@Override
	public List<QuestionDTO> getAllQuestionFromFileSource(NdtMethod ndtMethod, TestTypes testType) {
		List<QuestionDTO> questionDTOList = new ArrayList<>();

		String pathOdsFile = RESOURCE_PATH + ndtMethod.toString() + ODS_FILE_SUFFIX;

		Sheet sheet = null;
		URL url = getClass().getResource(pathOdsFile);
		try {
			
			URI uri = url.toURI();
			File file = new File(uri);
			sheet = SpreadSheet.createFromFile(file).getSheet(srcSheetMap.get(testType));
			sheet.setColumnCount(SPREADSHEET_COLUMN_COUNT);
		}catch(URISyntaxException ex){
			ex.printStackTrace();
			System.out.println("NOT CORRECT URL: " + url.toString());
		}
		catch (IOException ex) {
			ex.printStackTrace();
			System.out.println("CAN NOT TO OPEN SHEET: "+ ex.getMessage());
		}

		List<QuestionRowEntity> rows = new ArrayList<>();
		if (sheet == null) throw new NullPointerException("Sheet is null in"+pathOdsFile);
		for (int rowNum = 0; ; rowNum++) {
			if (sheet.getValueAt(0, rowNum) == null) throw new NullPointerException("VALUE in Cell (0,"+rowNum+") is NULL");

			String text = sheet.getValueAt(0, rowNum).toString().trim();
			String isCorrect = sheet.getValueAt(1,rowNum).toString();
			if (text.equals("END"))
				break;
			rows.add(new QuestionRowEntity(text, isCorrect));

		}
		
		List<QuestionRowEntity> buffer = new ArrayList<>();
		for (QuestionRowEntity questionRowEntity : rows) {
			if (StringUtils.isBlank(questionRowEntity.getText())) {
				QuestionDTO q = getQuestionFromRows(buffer,ndtMethod,testType);
				questionDTOList.add(q);
				buffer.clear();
			} else {
				buffer.add(questionRowEntity);
			}
		}

		return questionDTOList;
	}

	@Override
	public QuestionDTO getQuestionDtoFromDbEntity(QuestionDB questionDB) {
		QuestionDTO questionDTO = new QuestionDTO(questionDB.getQuestionText(), questionDB.getQuestionNumber(),
				questionDB.getNdtMethod(), questionDB.getTestTypes());
		questionDB.getAnswerVariants().forEach(answerVariantDB ->
				questionDTO.addAnswerDTO(answerVariantDB.getAnswerText(), answerVariantDB.isCorrect()));
		return questionDTO;
	}

	/**
	 *
	 * @param textRows - rows of the text obtained from ods file.
	 *                    The first row is a questionText and other rows are variants
	 * @return Question object with fields
	 * NOTE:  all variants of Question set as incorrect (false)
	 */
	private QuestionDTO getQuestionFromRows(List<QuestionRowEntity> textRows, NdtMethod ndtMethod, TestTypes testTypes){
		if (textRows.isEmpty() || textRows.get(0).getText().isEmpty())
			throw new IllegalArgumentException("List of question rows is empty");

		String firstRow = textRows.get(0).getText();
		QuestionDTO questionDTO = new QuestionDTO(getTextWithoutLeadNumber(firstRow),getNumberQuestionFromFirstRow(firstRow),ndtMethod, testTypes);

		for (QuestionRowEntity questionRowEntity : textRows){
			if (questionRowEntity.getText().equals(firstRow))
				continue;
			questionDTO.addAnswerDTO(questionRowEntity.getText(),questionRowEntity.isCorrect());
		}
		return questionDTO;
	}

	private String getTextWithoutLeadNumber(String textWithLeadNumber){
		if (textWithLeadNumber.contains(QUESTION_NUMBER_SEPARATOR))
			return textWithLeadNumber.substring(textWithLeadNumber.indexOf(QUESTION_NUMBER_SEPARATOR)+2);
		else
			throw new TCreatorException("Incorrect format of question text. Text:"+textWithLeadNumber);
	}

	private static class QuestionRowEntity{
		private String text;
		private String isCorrect;

		public QuestionRowEntity(String text, String isCorrect){
			this.isCorrect = isCorrect;
			this.text = text;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public boolean isCorrect() {
			return !StringUtils.isBlank(isCorrect);
		}

		public void setCorrect(String correct) {
			isCorrect = correct;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			QuestionRowEntity that = (QuestionRowEntity) o;
			return Objects.equals(getText(), that.getText());
		}

		@Override
		public int hashCode() {
			return Objects.hash(getText());
		}
	}
}
