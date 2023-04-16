package com.wamk.carInsurence.dtos;

import java.io.Serializable;
import java.time.Instant;

public class AcidentDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Instant data_acidente;
	private Instant hora_acidente;
	private String local_acidente;
	
	public AcidentDTO() {
	}

	public AcidentDTO(Instant data_acidente, Instant hora_acidente, String local_acidente) {
		super();
		this.data_acidente = data_acidente;
		this.hora_acidente = hora_acidente;
		this.local_acidente = local_acidente;
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
