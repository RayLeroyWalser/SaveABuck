package com.caux.saveabuck.model;

public class Group {
	private Integer id;
	private String 	title;
	private Integer color;
	
	public Group(Integer id, String title, Integer color) {
		super();
		this.id = id;
		this.title = title;
		this.color = color;
	}

	public Group(String title, Integer color) {
		super();
		this.id = -1;
		this.title = title;
		this.color = color;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getColor() {
		return color;
	}

	public void setColor(Integer color) {
		this.color = color;
	}	


}
