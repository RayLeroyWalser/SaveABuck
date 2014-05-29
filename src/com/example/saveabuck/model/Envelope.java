package com.example.saveabuck.model;

public class Envelope {
	private Integer id;
	private String 	title;
	
	private Double	resetToValue;

	public Envelope(Integer id, String title, Double resetToValue) {
		super();
		this.id = id;
		this.title = title;
		this.resetToValue = resetToValue;
	}
	
	public Envelope(String title, Double resetToValue) {
		super();
		this.id = -1;
		this.title = title;
		this.resetToValue = resetToValue;
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

	public Double getResetToValue() {
		return resetToValue;
	}

	public void setResetToValue(Double resetToValue) {
		this.resetToValue = resetToValue;
	}	

	
	
}
