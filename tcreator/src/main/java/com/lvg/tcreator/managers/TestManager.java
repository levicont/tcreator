package com.lvg.tcreator.managers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

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
	
	
	private static final String EXCEL_FILE_SUFFIX = "-II.xls";
	private static final String DATA_PATH = "e:/work/git/tcreator/tcreator/src/main/webapp/WEB-INF/data/";
	private static Map<TestTypes, Integer> map = new HashMap<TestTypes, Integer>();
	private Map<TestTypes, List<Question>> allQuestionsMap = new HashMap<TestTypes, List<Question>>();
	
	private Order order;
	private String pathToExcelDataFiles;
	
	static{
		map.put(TestTypes.TOTAL_TEST, 0);
		map.put(TestTypes.SPEC_TEST, 1);
		map.put(TestTypes.SPEC_6_SECTOR_TEST, 2);
		map.put(TestTypes.SPEC_7_SECTOR_TEST, 3);
		map.put(TestTypes.SPEC_8_SECTOR_TEST, 4);
	}
		
	public TestManager(Order order){
		this.order = order;
		initAllQuestionsMap(order.getNdtMethod());
	}
	
	private void initAllQuestionsMap(NdtMethod method){
		allQuestionsMap.put(TestTypes.TOTAL_TEST, getAllQuestionFromExcel(TestTypes.TOTAL_TEST));
		allQuestionsMap.put(TestTypes.SPEC_TEST, getAllQuestionFromExcel(TestTypes.SPEC_TEST));
		allQuestionsMap.put(TestTypes.SPEC_6_SECTOR_TEST, getAllQuestionFromExcel(TestTypes.SPEC_6_SECTOR_TEST));
		allQuestionsMap.put(TestTypes.SPEC_7_SECTOR_TEST, getAllQuestionFromExcel(TestTypes.SPEC_7_SECTOR_TEST));
		allQuestionsMap.put(TestTypes.SPEC_8_SECTOR_TEST, getAllQuestionFromExcel(TestTypes.SPEC_8_SECTOR_TEST));
		
	}
	
	public static void main(String[] args){
		TestManager tm = new TestManager(OrderManager.getDefaultOrder());		
		List<Test> list = tm.createTestListFromExcel();
				
		for(Test t : list){
			System.out.println(t.toString());
		}		
	}
		
	
	public List<Test> createTestListFromExcel(){
		List<Test> tests = new ArrayList<Test>();	
		int variantCount = order.getVariantCount();	
		if(order.getIsTotalTest())
			tests.addAll(getSameTestsWithDiferentVariants(TestTypes.TOTAL_TEST, variantCount));
		if(order.getIsSpecTest())
			tests.addAll(getSameTestsWithDiferentVariants(TestTypes.SPEC_TEST, variantCount));
		if(order.getIs6sector())
			tests.addAll(getSameTestsWithDiferentVariants(TestTypes.SPEC_6_SECTOR_TEST, variantCount));
		if(order.getIs7sector())
			tests.addAll(getSameTestsWithDiferentVariants(TestTypes.SPEC_7_SECTOR_TEST, variantCount));
		if(order.getIs8sector())
			tests.addAll(getSameTestsWithDiferentVariants(TestTypes.SPEC_8_SECTOR_TEST, variantCount));
				
		return tests;
	}	
	
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getPathToExcelDataFiles() {
		return pathToExcelDataFiles;
	}

	public void setPathToExcelDataFiles(String pathToExcelDataFiles) {
		this.pathToExcelDataFiles = pathToExcelDataFiles;
	}

	private List<Test> getSameTestsWithDiferentVariants(TestTypes testType, int variantCount){
		List<Test> tests = new ArrayList<Test>();
		for(int i = 1; i <= variantCount; i++){
			Test t = getTestFromExcel(testType);
			t.setVariantNumber(i);
			tests.add(t);
		}		
		return tests;
	}
	
	private Test getTestFromExcel(TestTypes testType){
		Test test = new Test();
		Set<Question> questions = getRandomQuestionsFromList(allQuestionsMap.get(testType), calculateQuestionsCount(testType)); 
		test.setQuestions(questions);
		test.setId(generateTestId());
		test.setSize(questions.size());
		test.setTitle(getTestTitle(testType));
		return test;
	}
	
	private int generateTestId(){
		return (int)System.currentTimeMillis();
	}
	
	private Set<Question> getRandomQuestionsFromList(List<Question> questList, int countQuestions){
		Set<Question> result = new TreeSet<Question>();
		
		while(result.size() < countQuestions ){
			int index = generateIndex(questList.size());
			result.add(questList.get(index));
		}		
		return result;
	}
	
	private int generateIndex(int max){
		Random random = new Random();
		int num = random.nextInt(max);
		return num;
	}
	
	
	private List<Question> getAllQuestionFromExcel(TestTypes testType){
		List<Question> questions = new ArrayList<>();
		String method = order.getNdtMethod().toString();
		String path = pathToExcelDataFiles == null ? DATA_PATH : pathToExcelDataFiles;
		
		StringBuilder pathXlsFile = new StringBuilder().append(path).append(method).append(EXCEL_FILE_SUFFIX);		
		
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
		List<String> rows = new ArrayList<String>();
		
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
		List<String> buffer = new ArrayList<String>();
		for(String s : rows){				
			if(s.trim().isEmpty()){
				Question q = new Question();				
				for(String row: buffer){
					q.getText().add(row);
				}
				try{
					q.setNumber(getNumberQuestionFromFirstRow(buffer.get(0).trim()));
				}catch(RuntimeException ex){
					System.out.println("Exception: "+ex.getMessage());
					System.out.println("Invalid excel row is: "+s);					
				}
				questions.add(q);
				buffer.clear();
				continue;
			}
			buffer.add(s);
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
			System.out.println("INVALID QUESTION: "+ ex.getMessage());	
			System.out.println("firstRow:  "+ firstRow);
			System.out.println("IndexOf('. '): "+firstRow.indexOf(". "));
			System.out.println("*****************");
			throw new RuntimeException("INVALID STRING PARAMETER");
		}
		return Integer.parseInt(num);
	}
	
	private int calculateQuestionsCount(TestTypes testType){
		int sectorsCount = getSectorsCount();
		
		if (testType == TestTypes.TOTAL_TEST){
			if(order.getNdtMethod() == NdtMethod.RT || order.getNdtMethod() == NdtMethod.UT)
				return TOTAL_TEST_UT_RT_QUESTIONS_COUNT;
			else
				return TOTAL_TEST_MT_VT_PT_QUESTIONS_COUNT;
		}else if (testType == TestTypes.SPEC_TEST){
			return SPEC_TEST_QUESTIONS_COUNT;
		}else if(sectorsCount == 3){
			return SPEC_TEST_THREE_SECTOR_QUESTIONS_COUNT;
		}else if(sectorsCount == 2){
			return SPEC_TEST_TWO_SECTOR_QUESTIONS_COUNT;
		}else
			return SPEC_TEST_ONE_SECTOR_QUESTIONS_COUNT;
	}
	
	private int getSectorsCount(){
		if(order.getIs6sector() && order.getIs7sector() && order.getIs8sector())
			return 3;
		else if(order.getIs6sector() && order.getIs7sector())
			return 2;
		else if(order.getIs6sector() && order.getIs8sector())
			return 2;
		else if(order.getIs7sector() && order.getIs8sector())
			return 2;
		else
			return 1;
		
		
		
	}
	
	private String getTestTitle(TestTypes testType){
		if(testType == TestTypes.TOTAL_TEST)
			return TOTAL_TEST_TITLE;
		if(testType == TestTypes.SPEC_TEST)
			return SPEC_TEST_TITLE;
		if(testType == TestTypes.SPEC_6_SECTOR_TEST)
			return SPEC_TEST_6_SECTOR_TITLE;
		if(testType == TestTypes.SPEC_7_SECTOR_TEST)
			return SPEC_TEST_7_SECTOR_TITLE;
		if(testType == TestTypes.SPEC_8_SECTOR_TEST)
			return SPEC_TEST_8_SECTOR_TITLE;
		
		throw new RuntimeException("Ivalid test title");		
	}

}
