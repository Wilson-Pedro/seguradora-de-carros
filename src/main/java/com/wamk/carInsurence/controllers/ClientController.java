package com.wamk.carInsurence.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wamk.carInsurence.dtos.ClientDTO;
import com.wamk.carInsurence.entities.Client;
import com.wamk.carInsurence.services.ClientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clients")
public class ClientController {

	@Autowired
	private ClientService clientService;
	
	@PostMapping
	public ResponseEntity<Client> adcionar(@Valid @RequestBody ClientDTO clientDTO) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(clientService.save(new Client(clientDTO)));
	}
	
	@GetMapping
	public ResponseEntity<List<ClientDTO>> findAll(){
		List<Client> list = clientService.getAll();
		List<ClientDTO> listDto = list.stream().map(x -> new ClientDTO(x)).toList();
		return ResponseEntity.ok().body(listDto);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Client> finfById(@PathVariable Long id){
		Client client = clientService.findById(id);
		return ResponseEntity.ok().body(client);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Client> updateClient(@Valid @RequestBody ClientDTO clientDTO, 
			@PathVariable Long id){
		Client clientUpdated = clientService.update(new Client(clientDTO), id);
		return ResponseEntity.ok().body(clientUpdated);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteClient(@PathVariable Long id){
		clientService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
