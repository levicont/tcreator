package com.lvg.tcreator.managers;

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
	private static final String DATA_PATH = "/webapp/WEB-INF/data/";
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
		List<Question> list = tm.getAllQuestion(NdtMethod.MT, TestTypes.TOTAL_TEST);
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
		 in  = getClass().getResourceAsStream(pathXlsFile.toString());
		 wb = new HSSFWorkbook(in);
		}catch(IOException ex){
			ex.printStackTrace();
		}
		
		Sheet sheet = wb.getSheetAt(map.get(testType));
		Iterator<Row> iter = sheet.iterator();
		StringBuilder sb = new StringBuilder();
		while(iter.hasNext()){
			Row row = iter.next();
			Iterator<Cell> cellIter  = row.iterator();
			while(cellIter.hasNext()){
				Cell cell = cellIter.next();
				sb.append(cell.getStringCellValue());
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
		return questions;
	}

}
