package com.lvg.tcreator.models;

import java.util.Set;

public class Test {
	
	private long id;
	private String title;
	private int size;
	private int variantNumber;
	private Set<Question> questions;
	private Order order;
	
	public Test(){}
	
	
	public long getId() {
		return id;
	}


	public void setId(long id) {
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
	

	public Order getOrder() {
		return order;
	}


	public void setOrder(Order order) {
		this.order = order;
	}


	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(title).append("\n");
		result.append("variant ").append(variantNumber).append("\n");
		for(Question q : questions){
			result.append(q.toString()).append("\n\n");
		}
		result.append(id);
		return result.toString();
	}
	
	
	
}
