package com.lvg.tcreator.models;

import java.util.Set;

public class Test {
	
	private int id;
	private String title;
	private int size;
	private int variantNumber;
	private Set<Question> questions;		
	
	public Test(){}
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public int getSize() {
		return size;
	}


	public void setSize(int size) {
		this.size = size;
	}


	public int getVariantNumber() {
		return variantNumber;
	}


	public void setVariantNumber(int variantNumber) {
		this.variantNumber = variantNumber;
	}


	public Set<Question> getQuestions() {
		return questions;
	}


	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}


	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(title).append("<br>");
		result.append("variant ").append(variantNumber).append("<br>");
		for(Question q : questions){
			result.append(q.toString()).append("<br><br>");
		}
		result.append(id);
		return result.toString();
	}
	
	
	
}
