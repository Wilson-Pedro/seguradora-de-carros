package com.wamk.carInsurence.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wamk.carInsurence.entities.Acident;
import com.wamk.carInsurence.entities.repositories.AcidentRepository;

@Service
public class AcidentService {

	@Autowired
	private AcidentRepository acidentRepository;

	public Acident save(Acident acident) {
		return acidentRepository.save(acident);
	}

	public List<Acident> findAll() {
		return acidentRepository.findAll();
	}

	public Optional<Acident> findById(Long id) {
		return acidentRepository.findById(id);
	}

	public void delete(Acident acident) {
		acidentRepository.delete(acident);
	}
}
