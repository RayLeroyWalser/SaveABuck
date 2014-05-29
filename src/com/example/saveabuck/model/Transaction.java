package com.example.saveabuck.model;

public class Transaction {
	private Integer id;
	private Integer categoria;
	private Integer conta;
	
	private long 	timestamp;
	private double	value;
	
	public Transaction(Integer id, String title, long timestamp, Integer categoria, Integer conta, double value) {
		super();
		this.id = id;
		this.timestamp = timestamp;
		this.categoria = categoria;
		this.conta = conta;
		this.value = value;
	}
	
	public Transaction(Integer categoria, Integer conta, double value) {
		super();

		this.id = -1;
		this.categoria = categoria;
		this.conta = conta;
		
		java.util.Date date= new java.util.Date();
		this.timestamp = date.getTime();
		this.value = value;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCategoria() {
		return categoria;
	}

	public void setCategoria(Integer categoria) {
		this.categoria = categoria;
	}

	public Integer getConta() {
		return conta;
	}

	public void setConta(Integer conta) {
		this.conta = conta;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}	
}
