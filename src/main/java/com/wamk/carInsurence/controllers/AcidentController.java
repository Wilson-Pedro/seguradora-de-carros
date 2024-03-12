package com.wamk.carInsurence.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wamk.carInsurence.dtos.AcidentDTO;
import com.wamk.carInsurence.entities.Acident;
import com.wamk.carInsurence.services.AcidentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/acidents")
public class AcidentController {

	@Autowired
	private AcidentService acidentService;
	
	@PostMapping
	public ResponseEntity<Acident> addAcident(@Valid @RequestBody AcidentDTO acidentDTO){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(acidentService.save(new Acident(acidentDTO)));
	}
	
	@GetMapping
	public ResponseEntity<List<Acident>> findAll(){
		return ResponseEntity.ok(acidentService.findAll());
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Acident> findById(@PathVariable Long id){
		return ResponseEntity.ok(acidentService.findById(id));
	}
	
//	@PutMapping(value = "/{id}")
//	public ResponseEntity<Acident> updateAcident(@Valid @RequestBody AcidentDTO acidentDTO, 
//			@PathVariable Long id){
//		Acident acident = acidentService.update(new Acident(acidentDTO), id);
//		return ResponseEntity.ok(acident);
//	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteAcident(@PathVariable Long id){
		acidentService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
