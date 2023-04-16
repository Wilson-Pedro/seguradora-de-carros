package com.wamk.carInsurence.entities;

import java.io.Serializable;
import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_acident")
public class Acident implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Instant data_acidente;
	private Instant hora_acidente;
	private String local_acidente;
	
	public Acident() {
	}

	public Acident(Long id, Instant data_acidente, Instant hora_acidente, String local_acidente) {
		super();
		this.id = id;
		this.data_acidente = data_acidente;
		this.hora_acidente = hora_acidente;
		this.local_acidente = local_acidente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getData_acidente() {
		return data_acidente;
	}

	public void setData_acidente(Instant data_acidente) {
		this.data_acidente = data_acidente;
	}

	public Instant getHora_acidente() {
		return hora_acidente;
	}

	public void setHora_acidente(Instant hora_acidente) {
		this.hora_acidente = hora_acidente;
	}

	public String getLocal_acidente() {
		return local_acidente;
	}

	public void setLocal_acidente(String local_acidente) {
		this.local_acidente = local_acidente;
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
		Acident other = (Acident) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
