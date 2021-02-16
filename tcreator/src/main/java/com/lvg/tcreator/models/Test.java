package com.lvg.tcreator.models;

import java.util.Set;

public class Test {
	
	private long id;
	private String title;
	private int size;
	private int variantNumber;
	private Set<Question> questions;
	private OrderDTO orderDTO;
	
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
	

	public OrderDTO getOrder() {
		return orderDTO;
	}


	public void setOrder(OrderDTO orderDTO) {
		this.orderDTO = orderDTO;
	}


	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(getTitle()).append("\n");
		result.append("variant ").append(getVariantNumber()).append("\n");
		for(Question q : getQuestions()){
			result.append(q.toString()).append("\n\n");
		}
		result.append(getId());
		return result.toString();
	}
	
	
	
}
