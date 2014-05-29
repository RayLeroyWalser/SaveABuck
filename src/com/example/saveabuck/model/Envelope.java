package com.example.saveabuck.model;

public class Envelope {
	private Integer id;
	private String 	title;
	private Integer	type; 
		
	public Envelope(Integer id, String title, Integer type) {
		super();
		this.id = id;
		this.title = title;
		this.type = type;
	}
	
	public Envelope(String title, Integer type) {
		super();
		this.title = title;
		this.type = type;
	}

	public String getTitle() {
		return title;
	}
	
	public Integer getType() {
		return type;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setType(Integer type) {
		this.type = type;
	}
}
