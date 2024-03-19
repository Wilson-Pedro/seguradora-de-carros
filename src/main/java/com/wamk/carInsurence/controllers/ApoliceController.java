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

import com.wamk.carInsurence.dtos.ApoliceDTO;
import com.wamk.carInsurence.entities.Apolice;
import com.wamk.carInsurence.services.ApoliceService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/apolices")
public class ApoliceController {
	
	@Autowired
	private ApoliceService apoliceService;
	
	@PostMapping
	public ResponseEntity<Apolice> addApolice(@Valid @RequestBody ApoliceDTO apoliceDTO){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(apoliceService.save(new Apolice(apoliceDTO)));
	}
	
	@GetMapping
	public ResponseEntity<List<Apolice>> findAll(){
		return ResponseEntity.ok(apoliceService.findAll());
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Apolice> findById(@PathVariable Long id){
		return ResponseEntity.ok(apoliceService.findById(id));
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Apolice> updateApolice(@PathVariable Long id,
			@Valid @RequestBody ApoliceDTO apoliceDTO){
		Apolice apolice = apoliceService.update(new Apolice(apoliceDTO), id);
		return ResponseEntity.ok(apolice);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteApolice(@PathVariable Long id){
		apoliceService.delete(id);
		return ResponseEntity.noContent().build();
		
	}
}
