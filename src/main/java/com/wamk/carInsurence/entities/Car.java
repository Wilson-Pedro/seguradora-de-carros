package com.wamk.carInsurence.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wamk.carInsurence.dtos.CarDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_car")
public class Car implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String register;
	
	@Column(unique=true)
	private String plate;
	
	private String brand;
	
	@JsonIgnore
	@OneToOne(mappedBy = "car")
	private Apolice apolice;
	
	@JsonIgnore
	@OneToMany(mappedBy = "car")
	private List<Acident> acidents = new ArrayList<>();
	
	public Car() {
	}

	public Car(CarDTO carDTO) {
		register = carDTO.getRegister();
		plate = carDTO.getPlate();
		brand = carDTO.getBrand();
	}
	

	public Car(Long id, String register, String plate, String brand) {
		super();
		this.id = id;
		this.register = register;
		this.plate = plate;
		this.brand = brand;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Apolice getApolice() {
		return apolice;
	}

	public void setApolice(Apolice apolice) {
		this.apolice = apolice;
	}
	
	public List<Acident> getAcidents() {
		return acidents;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
