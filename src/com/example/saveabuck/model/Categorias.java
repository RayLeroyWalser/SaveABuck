package com.example.saveabuck.model;

public class Categorias {
	private Integer id;
	private String 	title;

	public Categorias(Integer id, String title) {
		super();
		this.id = id;
		this.title = title;
	}
	
	public Categorias(String title) {
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
