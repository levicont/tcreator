package com.lvg.tcreator.models;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component("order")
public class Order {
	private NdtMethod ndtMethod;
	private String number;
	private Date date;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
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
	
}
