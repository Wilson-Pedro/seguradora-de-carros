package com.wamk.carInsurence.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wamk.carInsurence.dtos.ClientDTO;
import com.wamk.carInsurence.entities.Client;
import com.wamk.carInsurence.services.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {

	@Autowired
	private ClientService clientService;
	
	@PostMapping
	public ResponseEntity<Client> adcionar(@RequestBody ClientDTO clientDTO) {
		var client = new Client();
		BeanUtils.copyProperties(clientDTO, client);
		return ResponseEntity.status(HttpStatus.CREATED).body(clientService.save(client));
	}
}
