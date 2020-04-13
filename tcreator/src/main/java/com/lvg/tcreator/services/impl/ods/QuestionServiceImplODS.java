package com.lvg.tcreator.services.impl.ods;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

import com.lvg.tcreator.models.NdtMethod;
import com.lvg.tcreator.models.Question;
import com.lvg.tcreator.models.TestTypes;
import com.lvg.tcreator.services.impl.QuestionServiceImpl;

public class QuestionServiceImplODS extends QuestionServiceImpl {
	private static final String ODS_FILE_SUFFIX = "-II.ods";
	private static final String RESOURCE_PATH = "/ods/";

	
	@Override
	public List<Question> getAllQuestion(NdtMethod ndtMethod, TestTypes testType) {
		List<Question> questions = new ArrayList<>();
		String method = ndtMethod.toString();
		String pathOdsFile = RESOURCE_PATH + method + ODS_FILE_SUFFIX;
		Sheet sheet = null;
		URL url = getClass().getResource(pathOdsFile);
		try {
			
			URI uri = url.toURI();
			File file = new File(uri);
			sheet = SpreadSheet.createFromFile(file).getSheet(srcSheetMap.get(testType));
		}catch(URISyntaxException ex){
			ex.printStackTrace();
			System.out.println("NOT CORRECT URL: " + url.toString());
		}
		catch (IOException ex) {
			ex.printStackTrace();
			System.out.println("CAN NOT TO OPEN SHEET: "+ ex.getMessage());
		}

		List<String> rows = new ArrayList<>();
		if (sheet == null) throw new NullPointerException("Sheet is null in"+pathOdsFile);
		for (int rowNum = 0; ; rowNum++) {
			if (sheet.getValueAt(0, rowNum) == null) throw new NullPointerException("VALUE in Cell (0,"+rowNum+") is NULL");
			String cellValue = sheet.getValueAt(0, rowNum).toString().trim();
			if (cellValue.equals("END"))
				break;
			rows.add(cellValue);

		}
		
		List<String> buffer = new ArrayList<>();
		for (String s : rows) {
			if (s.trim().isEmpty()) {
				Question q = getQuestionFromRows(buffer);
				questions.add(q);
				buffer.clear();
			} else {
				buffer.add(s);
			}
		}

		return questions;
	}

	/**
	 *
	 * @param textRows - rows of the text obtained from ods file.
	 *                    The first row is a questionText and other rows are variants
	 * @return Question object with fields
	 * NOTE:  all variants of Question set as incorrect (false)
	 */
	private Question getQuestionFromRows(List<String> textRows){
		Question resultQuestion = new Question();
		if (textRows.isEmpty() || textRows.get(0).isEmpty()) throw new IllegalArgumentException("List of question rows is empty");

		String firstRow = textRows.get(0);
		resultQuestion.setQuestionText(firstRow);
		resultQuestion.setNumber(getNumberQuestionFromFirstRow(firstRow));

		for (String row : textRows){
			if (row.equals(firstRow))
				continue;
			resultQuestion.getVariants().put(row,Boolean.FALSE);
		}

		return resultQuestion;
	}
}
