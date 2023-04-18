package com.wamk.carInsurence.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wamk.carInsurence.entities.Client;
import com.wamk.carInsurence.entities.repositories.ClientRepository;

import jakarta.transaction.Transactional;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Transactional
	public Client save(Client client) {
		return clientRepository.save(client);
	}

	public List<Client> getAll() {
		return clientRepository.findAll();
	}

	public Optional<Client> findById(Long id) {
		return clientRepository.findById(id);
	}

	@Transactional
	public void delete(Client client) {
		clientRepository.delete(client);
	}
}
