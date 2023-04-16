package com.wamk.carInsurence.services;

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
	
	
}
