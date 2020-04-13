package com.lvg.tcreator.models;

import java.util.*;

public class Question implements Comparable<Question>{
	
	private int number;
	private String questionText;
	Map<String, Boolean> variants = new TreeMap<>();

	public Question(){
		this.number = 0;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}


	public String getQuestionText() {
		return questionText;
	}

	public Map<String, Boolean> getVariants() {
		return variants;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public Set<String> getVariantKeys(){
		return new TreeSet<>(variants.keySet());
	}

	@Override
	public boolean equals(Object obj) {
		
		if (!(obj instanceof Question))
			return false;
		Question question = (Question)obj;
		return question.getNumber() == number;
	}
	
	//TODO Have to update this method
	@Override
	public String toString(){
		StringBuilder result = new StringBuilder();
		result.append(getQuestionText()).append("\n");
		for(String s : getVariantKeys()){
			result.append(s).append("\n");
		}
		return result.toString();
	}
	
	
	@Override
	public int compareTo(Question o) {
		return Integer.compare(number, o.number);
	}

}
