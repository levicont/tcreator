package com.lvg.tcreator.services.impl.excel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.lvg.tcreator.models.NdtMethod;
import com.lvg.tcreator.models.Question;
import com.lvg.tcreator.models.TestTypes;
import com.lvg.tcreator.services.impl.QuestionServiceImpl;

public class QuestionServiceImplExcel extends QuestionServiceImpl {
	private static final String EXCEL_FILE_SUFFIX = "-II.xls";
	private static final String RESOURCE_PATH = "/xls/";	
	
	@Override
	public List<Question> getAllQuestion(NdtMethod ndtMethod, TestTypes testType) {
		List<Question> questions = new ArrayList<>();
		String method = ndtMethod.toString();		
		StringBuilder pathXlsFile = new StringBuilder().append(RESOURCE_PATH+method+EXCEL_FILE_SUFFIX);

		InputStream in = null;
		HSSFWorkbook wb = null;
		
		try {
			in = getClass().getResourceAsStream(pathXlsFile.toString());
			System.out.println("IN STREM: "+in);
			wb = new HSSFWorkbook(in);
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		Sheet sheet = wb.getSheetAt(srcSheetMap.get(testType));
		Iterator<Row> iter = sheet.iterator();
		List<String> rows = new ArrayList<String>();

		while (iter.hasNext()) {
			Row row = iter.next();
			Iterator<Cell> cellIter = row.iterator();
			while (cellIter.hasNext()) {
				Cell cell = cellIter.next();
				String cellValue = cell.getStringCellValue().trim();					
				rows.add(cellValue);				
			}
			if ((rows.size() - 1) < row.getRowNum()) {
				rows.add(rows.size() - 1, "");
			}
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
		try {
			in.close();

		} catch (IOException ex) {
		}

		return questions;
	}

	
	

}
