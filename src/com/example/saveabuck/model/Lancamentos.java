package com.example.saveabuck.model;

public class Lancamentos {
	private Integer id;
	private String 	title;
	private Integer timestamp;
	private Integer categoria;
	private Integer conta;
	
	public Lancamentos(Integer id, String title, Integer timestamp, Integer conta, Integer categoria) {
		super();
		this.id = id;
		this.title = title;
		this.timestamp = timestamp;
		this.conta = conta;
		this.categoria = categoria;
	}

	public Lancamentos(String title, Integer timestamp, Integer conta, Integer categoria) {
		super();
		this.title = title;
		this.timestamp = timestamp;
		this.conta = conta;
		this.categoria = categoria;
	}
	
	public Lancamentos(String title, Integer conta, Integer categoria) {
		super();
		this.title = title;
		this.conta = conta;
		this.categoria = categoria;
	}

	public Integer getCategoria() {
		return categoria;
	}

	public Integer getConta() {
		return conta;
	}

	public Integer getId() {
		return id;
	}

	public Integer getTimestamp() {
		return timestamp;
	}

	public String getTitle() {
		return title;
	}

	public void setCategoria(Integer categoria) {
		this.categoria = categoria;
	}

	public void setConta(Integer conta) {
		this.conta = conta;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setTimestamp(Integer timestamp) {
		this.timestamp = timestamp;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
