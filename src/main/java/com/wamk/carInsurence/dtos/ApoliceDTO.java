package com.wamk.carInsurence.dtos;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;

public class ApoliceDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "value is mandatory")
	private Double value;
	
	public ApoliceDTO() {
	}

	public ApoliceDTO(Double value) {
		this.value = value;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
}
