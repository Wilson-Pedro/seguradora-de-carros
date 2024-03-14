package com.wamk.carInsurence.dtos;

import java.io.Serializable;

import com.wamk.carInsurence.entities.Car;

import jakarta.validation.constraints.NotNull;

public class CarDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "register is mandatory")
	private String register;
	
	@NotNull(message = "plate is mandatory")
	private String plate;
	
	@NotNull(message = "brand is mandatory")
	private String brand;
	
	public CarDTO() {
	}

	public CarDTO(String register, String brand, String plate) {
		this.register = register;
		this.brand = brand;
		this.plate = plate;
	}
	
	public CarDTO(Car car) {
		register = car.getRegister();
		plate = car.getPlate();
		brand = car.getBrand();
	}

	public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		this.register = register;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
}
