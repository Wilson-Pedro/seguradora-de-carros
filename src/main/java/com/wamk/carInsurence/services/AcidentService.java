package com.wamk.carInsurence.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wamk.carInsurence.entities.Acident;
import com.wamk.carInsurence.entities.repositories.AcidentRepository;
import com.wamk.carInsurence.exceptionhandle.exceptions.EntityNotFoundException;

@Service
public class AcidentService {

	@Autowired
	private AcidentRepository acidentRepository;

	@Transactional
	public Acident save(Acident acident) {
		return acidentRepository.save(acident);
	}

	public List<Acident> findAll() {
		return acidentRepository.findAll();
	}

	public Acident findById(Long id) {
		return acidentRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException());
	}

	@Transactional
	public void delete(Long id) {
		acidentRepository.delete(acidentRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException()));
	}
}
