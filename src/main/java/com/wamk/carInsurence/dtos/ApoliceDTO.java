package com.wamk.carInsurence.dtos;

import java.io.Serializable;

public class ApoliceDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Double value;
	
	public ApoliceDTO() {
	}

	public ApoliceDTO(Double value) {
		super();
		this.value = value;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
}
