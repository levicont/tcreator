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

import com.lvg.tcreator.config.DataSourceType;
import com.lvg.tcreator.factories.ServiceFactory;
import com.lvg.tcreator.models.NdtMethod;
import com.lvg.tcreator.models.Question;
import com.lvg.tcreator.models.TestTypes;
import com.lvg.tcreator.services.QuestionService;
import com.lvg.tcreator.services.impl.QuestionServiceImpl;

public class QuestionServiceImplODS extends QuestionServiceImpl {
	private static final String ODS_FILE_SUFFIX = "-II.ods";
	private static final String RESOURCE_PATH = "/ods/";

	public static void main (String[] args){
		QuestionService qService = ServiceFactory.getQuestionService(DataSourceType.ODS);
		System.out.println("Starting");
		List<Question> list = qService.getAllQuestion(NdtMethod.UT, TestTypes.SPEC_7_SECTOR_TEST);
		
		for(Question q : list){
			System.out.println(q.toString());
		}
		
		System.out.println("Stoped");
	}
	
	@Override
	public List<Question> getAllQuestion(NdtMethod ndtMethod, TestTypes testType) {
		List<Question> questions = new ArrayList<>();
		String method = ndtMethod.toString();
		StringBuilder pathOdsFile = new StringBuilder().append(RESOURCE_PATH + method + ODS_FILE_SUFFIX);
		Sheet sheet = null;
		URL url = getClass().getResource(pathOdsFile.toString());;
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

		List<String> rows = new ArrayList<String>();

		for (int rowNum = 0; ; rowNum++) {
			String cellValue = sheet.getValueAt(0, rowNum).toString().trim();
			if (cellValue.equals("END"))
				break;
			rows.add(cellValue);

		}
		
		List<String> buffer = new ArrayList<String>();
		for (String s : rows) {
			if (s.trim().isEmpty()) {
				Question q = new Question();
				for (String row : buffer) {
					q.getText().add(row);
				}
				String firstRow = null;
				try {
					firstRow = q.getText().get(0);
					q.setNumber(getNumberQuestionFromFirstRow(firstRow));
					questions.add(q);
				} catch (RuntimeException ex) {
					System.out.println("Exception: " + ex.getMessage());
					System.out.println("Invalid excel row; question firstRow is: " + firstRow);
					int i = 0;
					for (String str : buffer) {
						System.out.println(i + ": " + str);
						i++;
					}
					System.out.println("----------------");
				}
				buffer.clear();
				continue;
			} else {
				buffer.add(s);
			}
		}

		return questions;
	}
}
