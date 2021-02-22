package com.lvg.tcreator.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.*;

import static com.lvg.tcreator.config.R.OrderProps.*;

public class OrderDTO {
	private NdtMethod ndtMethod;

	@Size(min=MIN_NUMBER_SIZE_VALUE, max= MAX_NUMBER_SIZE_VALUE, message= INVALID_NUMBER_SIZE_MESSAGE)
	private String number;
	private LocalDate date;
	
	@Min(value= MIN_VARIANT_COUNT_VALUE, message = INVALID_VARIANT_COUNT_MESSAGE)
	@Max(value= MAX_VARIANT_COUNT_VALUE, message = INVALID_VARIANT_COUNT_MESSAGE)
	private int variantCount;
	
	private final List<ExamDTO> examDTOList = new ArrayList<>();
	private final Set<TestTypes> testTypesSet =
			new TreeSet<>(Comparator.comparingInt(testType -> Arrays.asList(TestTypes.values()).indexOf(testType)));

	public OrderDTO(){}
	public OrderDTO(NdtMethod ndtMethod,
					@Size(min = MIN_NUMBER_SIZE_VALUE, max = MAX_NUMBER_SIZE_VALUE, message = INVALID_NUMBER_SIZE_MESSAGE) String number,
					LocalDate date) {
		this.ndtMethod = ndtMethod;
		this.number = number;
		this.date = date;
	}

	public void addExam(ExamDTO examDTO){
		if (Objects.equals(examDTO.getNdtMethod(),ndtMethod)){
			examDTOList.add(examDTO);
			testTypesSet.add(examDTO.getTestType());
			return;
		}
		throw new IllegalArgumentException("OrderDTO and ExamDTO have different NdtMethod");
			
	}

	public void removeExam(ExamDTO examDTO){
		examDTOList.remove(examDTO);
		testTypesSet.remove(examDTO.getTestType());
	}

	public Set<TestTypes> getTestTypesSet() {
		return testTypesSet;
	}

	public int getSectorsCount(){
		int sectors = 0;
		if (getIs6sector())
			sectors++;
		if (getIs7sector())
			sectors++;
		if (getIs8sector())
			sectors++;
		return sectors;
	}

	public List<ExamDTO> getExamDTOList() {
		return examDTOList;
	}

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
		return testTypesSet.contains(TestTypes.TOTAL_TEST);
	}
	public boolean getIsSpecTest() {
		return testTypesSet.contains(TestTypes.SPEC_TEST);
	}
	public boolean getIs6sector() {
		return testTypesSet.contains(TestTypes.SPEC_6_SECTOR_TEST);
	}
	public boolean getIs7sector() {
		return testTypesSet.contains(TestTypes.SPEC_7_SECTOR_TEST);
	}
	public boolean getIs8sector() {
		return testTypesSet.contains(TestTypes.SPEC_8_SECTOR_TEST);
	}

	public void setIsTotalTest(boolean totalTest){testTypesSet.add(TestTypes.TOTAL_TEST);}
	public void setIsSpecTest(boolean specTest){testTypesSet.add(TestTypes.SPEC_TEST);}
	public void setIs6sector(boolean spec6sector){testTypesSet.add(TestTypes.SPEC_6_SECTOR_TEST);}
	public void setIs7sector(boolean spec6sector){testTypesSet.add(TestTypes.SPEC_7_SECTOR_TEST);}
	public void setIs8sector(boolean spec6sector){testTypesSet.add(TestTypes.SPEC_8_SECTOR_TEST);}



}
