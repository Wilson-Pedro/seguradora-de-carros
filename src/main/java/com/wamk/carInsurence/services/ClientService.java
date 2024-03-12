package com.wamk.carInsurence.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wamk.carInsurence.entities.Client;
import com.wamk.carInsurence.entities.repositories.ClientRepository;
import com.wamk.carInsurence.exceptionhandle.exceptions.EntityNotFoundException;

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

	public Client findById(Long id) {
		return clientRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException());
	}
	
	@Transactional
	public Client update(Client client, Long id) {
		Client clientUpdated = findById(id);
		BeanUtils.copyProperties(client, clientUpdated);
		client.setId(id);
		return clientRepository.save(clientUpdated);
	}

	@Transactional
	public void delete(Long id) {
		clientRepository.delete(clientRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException()));
	}
}
