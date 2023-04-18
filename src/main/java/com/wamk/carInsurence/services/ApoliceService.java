package com.wamk.carInsurence.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wamk.carInsurence.entities.Apolice;
import com.wamk.carInsurence.entities.repositories.ApoliceRepository;

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

	public Optional<Apolice> findById(Long id) {
		return apoliceRepository.findById(id);
	}

	@Transactional
	public void delete(Apolice apolice) {
		apoliceRepository.delete(apolice);
	}
}
