package com.example.saveabuck.model;

public class Group {
	private Integer id;
	private String 	title;

	public Group(Integer id, String title) {
		super();
		this.id = id;
		this.title = title;
	}
	
	public Group(String title) {
		super();
		this.title = title;
	}	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
