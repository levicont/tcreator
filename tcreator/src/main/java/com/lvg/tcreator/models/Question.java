package com.lvg.tcreator.models;

public class Question implements Comparable<Question>{
	
	private int number;
	private String text;
	
	public Question(){
		this(0, "");
	}
	
	public Question(int number, String text){
		this.number = number;
		this.text = text;
	}
	

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
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
		return number + text;
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
