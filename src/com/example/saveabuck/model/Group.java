package com.example.saveabuck.model;

public class Group {
	private Integer id;
	private String 	title;
	private Integer icon;
	
	public Group(Integer id, String title, Integer icon) {
		super();
		this.id = id;
		this.title = title;
		this.icon = icon;
	}

	public Group(String title, Integer icon) {
		super();
		this.id = -1;
		this.title = title;
		this.icon = icon;
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

	public Integer getIcon() {
		return icon;
	}

	public void setIcon(Integer icon) {
		this.icon = icon;
	}	


}
