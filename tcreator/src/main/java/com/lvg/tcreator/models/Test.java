package com.lvg.tcreator.models;

import java.util.Set;

public class Test {
	
	private int id;
	private String title;
	private int size;
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


	public Set<Question> getQuestions() {
		return questions;
	}


	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}
	
	
	
}
