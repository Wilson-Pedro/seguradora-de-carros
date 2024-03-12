package com.wamk.carInsurence.dtos;

import java.io.Serializable;
import java.time.Instant;

import com.wamk.carInsurence.entities.Acident;

import jakarta.validation.constraints.NotNull;

public class AcidentDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "data_acidente is mandatory")
	private Instant data_acidente;
	
	@NotNull(message = "hora_acidente is mandatory")
	private Instant hora_acidente;
	
	@NotNull(message = "local_acidente is mandatory")
	private String local_acidente;
	
	public AcidentDTO() {
	}

	public AcidentDTO(Instant data_acidente, Instant hora_acidente, String local_acidente) {
		this.data_acidente = data_acidente;
		this.hora_acidente = hora_acidente;
		this.local_acidente = local_acidente;
	}
	
	public AcidentDTO(Acident acident) {
		data_acidente = acident.getData_acidente();
		hora_acidente = acident.getHora_acidente();
		local_acidente = acident.getLocal_acidente();
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
}
