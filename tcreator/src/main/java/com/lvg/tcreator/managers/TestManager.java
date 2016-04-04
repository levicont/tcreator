package com.lvg.tcreator.managers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.lvg.tcreator.models.NdtMethod;
import com.lvg.tcreator.models.Order;
import com.lvg.tcreator.models.Question;
import com.lvg.tcreator.models.Test;
import com.lvg.tcreator.models.TestTypes;

public class TestManager {
	private static final String EXCEL_FILE_SUFFIX = "-II.xls";
	private static final String DATA_PATH = "d:/work/git/tcreator/tcreator/src/main/webapp/WEB-INF/data/";
	private static Map<TestTypes, Integer> map = new HashMap<TestTypes, Integer>();
	
	static{
		map.put(TestTypes.TOTAL_TEST, 0);
		map.put(TestTypes.SPEC_TEST, 1);
		map.put(TestTypes.SPEC_6_SECTOR_TEST, 2);
		map.put(TestTypes.SPEC_7_SECTOR_TEST, 3);
		map.put(TestTypes.SPEC_8_SECTOR_TEST, 4);
	}
	
	public static void main(String[] args){
		TestManager tm = new TestManager();
		System.out.println();
		List<Question> list = tm.getAllQuestion(NdtMethod.MT, TestTypes.SPEC_6_SECTOR_TEST);
		for(Question q : list){
			System.out.println(q.getText());
		}
		
	}
	
	public static Test createTestFromExcel(Order order, TestTypes testType){
		Test test = new Test();
		
		
		
		
		
		return test;
	}
	
	private List<Question> getAllQuestion(NdtMethod method, TestTypes testType){
		List<Question> questions = new ArrayList<>();
		StringBuilder pathXlsFile = new StringBuilder().append(DATA_PATH).append(method.toString()).append(EXCEL_FILE_SUFFIX);
		
		InputStream in = null;
		HSSFWorkbook wb = null;
		try{
		 in  = new FileInputStream(pathXlsFile.toString());
		 wb = new HSSFWorkbook(in);
		}catch(IOException ex){
			ex.printStackTrace();
		}
		
		Sheet sheet = wb.getSheetAt(map.get(testType));
		Iterator<Row> iter = sheet.iterator();
		StringBuilder sb = new StringBuilder();
		List<String> rows = new ArrayList();
		
		while(iter.hasNext()){
			Row row = iter.next();
			Iterator<Cell> cellIter  = row.iterator();
			while(cellIter.hasNext()){
				Cell cell = cellIter.next();
				rows.add(cell.getStringCellValue()+"\n");
			}
			if((rows.size()-1) < row.getRowNum() ){
				rows.add(rows.size()-1, "\n");
			}
				
			
			
		}
		StringBuilder buffer = new StringBuilder();
		for(String s : rows){
				
			if(s.trim().isEmpty()){
				Question q = new Question();
				q.setNumber(getNumberQuestionFromFirstRow(buffer.toString().trim()));
				q.setText(buffer.toString().trim());
				questions.add(q);
				buffer.delete(0, buffer.length()-1);
				continue;
			}
			buffer.append(s);
		}
		try{
			in.close();
		}catch(IOException ex){}
		
		return questions;
	}
	
	private int getNumberQuestionFromFirstRow(String firstRow){
		String num = "-1";
		try{
			num = firstRow.substring(0,firstRow.indexOf(". "));
		}catch(StringIndexOutOfBoundsException ex){
			ex.printStackTrace();
			System.out.println("firstRow:  "+ firstRow);
		}
		return Integer.parseInt(num);
	}

}
