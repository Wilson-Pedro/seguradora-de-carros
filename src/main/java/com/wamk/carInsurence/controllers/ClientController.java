package com.wamk.carInsurence.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
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
	
	@GetMapping
	public ResponseEntity<List<Client>> findAll(){
		List<Client> list = clientService.getAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Optional<Client>> finfById(@PathVariable Long id){
		Optional<Client> client = clientService.findById(id);
		return ResponseEntity.ok().body(client);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Client> updateClient(@RequestBody ClientDTO clientDTO, 
			@PathVariable Long id){
		
		Optional<Client> clientOPTIONAL = clientService.findById(id);
		var client = new Client();
		BeanUtils.copyProperties(clientDTO, client);
		client.setId(clientOPTIONAL.get().getId());
		return ResponseEntity.ok().body(clientService.save(client));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> deleteClient(@PathVariable Long id){
		Optional<Client> obj = clientService.findById(id);
		clientService.delete(obj.get());
		return ResponseEntity.status(HttpStatus.OK).body("Client deleted successfully");
	}
}
