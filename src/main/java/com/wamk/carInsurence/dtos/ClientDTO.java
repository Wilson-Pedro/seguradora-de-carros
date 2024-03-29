	package com.wamk.carInsurence.dtos;

import java.io.Serializable;

import com.wamk.carInsurence.entities.Client;

import jakarta.validation.constraints.NotBlank;

public class ClientDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "name is mandatory")
	private String name;
	
	@NotBlank(message = "address is mandatory")
	private String address;
	
	public ClientDTO() {
	}

	public ClientDTO(String name, String address) {
		this.name = name;
		this.address = address;
	}
	
	public ClientDTO(Client client) {
		name = client.getName();
		address = client.getAddress();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
