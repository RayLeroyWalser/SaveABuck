package com.caux.saveabuck.model;

public class Transaction {
	private Integer id;
	private Integer group;
	private Integer envelope;
	
	private Long 	timestamp;
	private Double	value;
	

	
	public Transaction(Integer id, Integer group, Integer envelope,	Long timestamp, Double value) {
		super();
		this.id = id;
		this.group = group;
		this.envelope = envelope;
		this.timestamp = timestamp;
		this.value = value;
	}
	
	public Transaction(Integer group, Integer envelope,	Long timestamp, Double value) {
		super();
		this.id = -1;
		this.group = group;
		this.envelope = envelope;
		this.timestamp = timestamp;
		this.value = value;
	}	

	public Transaction(Integer group, Integer envelope, Double value) {
		super();

		this.id = -1;
		this.group = group;
		this.envelope = envelope;
		
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

	public Integer getGroup() {
		return group;
	}

	public void setGroup(Integer group) {
		this.group = group;
	}

	public Integer getEnvelope() {
		return envelope;
	}

	public void setEnvelope(Integer envelope) {
		this.envelope = envelope;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}


}
