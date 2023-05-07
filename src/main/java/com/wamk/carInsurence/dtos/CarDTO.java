package com.wamk.carInsurence.dtos;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;

public class CarDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "register is mandatory")
	private String register;
	
	@NotNull(message = "brand is mandatory")
	private String brand;
	
	public CarDTO() {
	}

	public CarDTO(String register, String brand) {
		super();
		this.register = register;
		this.brand = brand;
	}

	public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		this.register = register;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
}
