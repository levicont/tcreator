package com.lvg.tcreator.models;

import java.util.ArrayList;
import java.util.List;

public class Question implements Comparable<Question>{
	
	private int number;
	private List<String> text;
	
	public Question(){
		this(0, new ArrayList<String>());
	}
	
	public Question(int number, List<String> text){
		this.number = number;
		this.text = text;
	}
	

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public List<String> getText() {
		return text;
	}

	public void setText(List<String> text) {
		this.text = text;
	}

	
	@Override
	public boolean equals(Object obj) {
		
		if (obj == null || !(obj instanceof Question))
			return false;
		Question question = (Question)obj;
		if(question.getNumber() == number)
			return true;		
		
		return false;
	}
	
	@Override
	public String toString(){
		StringBuilder result = new StringBuilder();
		for(String s : text){
			result.append(s).append("\n");
		}
		return result.toString();
	}
	
	
	@Override
	public int compareTo(Question o) {
		if (number > o.number)
			return 1;
		if (number < o.number)
			return -1;
		return 0;
	}

}
