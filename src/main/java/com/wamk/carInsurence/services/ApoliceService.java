package com.wamk.carInsurence.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wamk.carInsurence.entities.Apolice;
import com.wamk.carInsurence.entities.repositories.ApoliceRepository;
import com.wamk.carInsurence.exceptionhandle.exceptions.EntityNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class ApoliceService {

	@Autowired
	private ApoliceRepository apoliceRepository;

	@Transactional
	public Apolice save(Apolice apolice) {
		return apoliceRepository.save(apolice);
	}

	public List<Apolice> findAll() {
		return apoliceRepository.findAll();
	}

	public Apolice findById(Long id) {
		return apoliceRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException());
	}
	
	@Transactional
	public Apolice update(Apolice apolice, Long id) {
		Apolice apoliceUpdated = findById(id);
		apoliceUpdated.setValue(apolice.getValue());
		return apoliceRepository.save(apoliceUpdated);
	}

	@Transactional
	public void delete(Long id) {
		apoliceRepository.delete(apoliceRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException()));
	}
}
