package com.lvg.tcreator.services.impl.excel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;

import com.lvg.tcreator.managers.OrderManager;
import com.lvg.tcreator.managers.TestManager;
import com.lvg.tcreator.models.Question;
import com.lvg.tcreator.models.Test;
import com.lvg.tcreator.services.TestService;

public class TestServiceImplExcel implements TestService{
	private final String FILE_NAME = "test.xls";
	private String path;
	
	
	public TestServiceImplExcel(String path) {
		this.path = path;
	}
	
	public static void main(String[] args){
		TestServiceImplExcel ts = new TestServiceImplExcel("d:/");
		TestManager tm = new TestManager(OrderManager.getDefaultOrder());
		List<Test> testList = tm.createTestList();
		ts.saveAll(testList);
	}
	
	public String getPath() {
		return path;
	}



	public void setPath(String path) {
		this.path = path;
	}



	@Override
	public void save(Test record) {
		throw new UnsupportedOperationException();	
	}

	@Override
	public Test get(long id) {
		throw new UnsupportedOperationException();	
	}
	
	

	public List<Test> getAll() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void update(Test record) {
		throw new UnsupportedOperationException();			
	}

	@Override
	public void delete(Test record) {
		throw new UnsupportedOperationException();	
	}

	@Override
	public void saveAll(List<Test> testList) {		
		FileOutputStream out = null;		
		Workbook wb = new HSSFWorkbook();		
		for(int i = 0; i < testList.size(); i++){
			int rowCount = 1;
			Test test = testList.get(i);
			Sheet sheet = wb.createSheet(WorkbookUtil.createSafeSheetName(test.getTitle()));
			Row row = sheet.createRow(rowCount);
			row.createCell(0).setCellValue(test.getTitle());
			for(Question question : test.getQuestions()){
				sheet.createRow(++rowCount).createCell(0);
				for(String line : question.getText()){
					sheet.createRow(++rowCount).createCell(0).setCellValue(line);
				}
			}			
		}
		
		try{
			out = new FileOutputStream(path+FILE_NAME);
			System.out.println(out.toString());
			wb.write(out);
		}catch(IOException ex){
			System.out.println("-------------  Can not open path " + path+FILE_NAME);			
		}finally{
			try{
				out.close();
				wb.close();
			}catch(IOException ex){
				//Do nothing
			}
		}		
		
	}
	

}
