package com.lvg.tcreator.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.lvg.tcreator.config.R.OrderProps.*;

public class OrderDTO {
	private NdtMethod ndtMethod;
	
	@Size(min=MIN_NUMBER_SIZE_VALUE, max= MAX_NUMBER_SIZE_VALUE, message= INVALID_NUMBER_SIZE_MESSAGE)
	private String number;
	
	
	private LocalDate date;
	
	@Min(value= MIN_VARIANT_COUNT_VALUE, message = INVALID_VARIANT_COUNT_MESSAGE)
	@Max(value= MAX_VARIANT_COUNT_VALUE, message = INVALID_VARIANT_COUNT_MESSAGE)
	private int variantCount;
	
	private boolean isTotalTest;
	private boolean isSpecTest;
	private boolean is6sector;
	private boolean is7sector;
	private boolean is8sector;
	
	public NdtMethod getNdtMethod(){
		return ndtMethod;
	}
	
	public void setNdtMethod(NdtMethod ndtMethod){
		this.ndtMethod = ndtMethod;
	}
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public int getVariantCount() {
		return variantCount;
	}
	public void setVariantCount(int variantCount) {
		this.variantCount = variantCount;
	}
	public boolean getIsTotalTest() {
		return isTotalTest;
	}
	public void setIsTotalTest(boolean isTotalTest) {
		this.isTotalTest = isTotalTest;
	}
	public boolean getIsSpecTest() {
		return isSpecTest;
	}
	public void setIsSpecTest(boolean isSpecTest) {
		this.isSpecTest = isSpecTest;
	}
	public boolean getIs6sector() {
		return is6sector;
	}
	public void setIs6sector(boolean is6sector) {
		this.is6sector = is6sector;
	}
	public boolean getIs7sector() {
		return is7sector;
	}
	public void setIs7sector(boolean is7sector) {
		this.is7sector = is7sector;
	}
	public boolean getIs8sector() {
		return is8sector;
	}
	public void setIs8sector(boolean is8sector) {
		this.is8sector = is8sector;
	}
	public List<TestTypes> getTestTypes(){
		List<TestTypes> testTypesList = new ArrayList<>();
		if (getIsTotalTest())
			testTypesList.add(TestTypes.TOTAL_TEST);
		if (getIsSpecTest())
			testTypesList.add(TestTypes.SPEC_TEST);
		if (getIs6sector())
			testTypesList.add(TestTypes.SPEC_6_SECTOR_TEST);
		if (getIs7sector())
			testTypesList.add(TestTypes.SPEC_7_SECTOR_TEST);
		if (getIs8sector())
			testTypesList.add(TestTypes.SPEC_8_SECTOR_TEST);
		return Collections.unmodifiableList(testTypesList);
	}
	
}
