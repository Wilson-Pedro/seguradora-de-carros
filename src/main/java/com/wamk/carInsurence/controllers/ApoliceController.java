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
		var apolice = new Apolice();
		BeanUtils.copyProperties(apoliceDTO, apolice);
		return ResponseEntity.status(HttpStatus.CREATED).body(apoliceService.save(apolice));
	}
	
	@GetMapping
	public ResponseEntity<List<Apolice>> findAll(){
		List<Apolice> list = apoliceService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Apolice> findById(@PathVariable Long id){
		Optional<Apolice> apolice = apoliceService.findById(id);
		return ResponseEntity.ok().body(apolice.get());
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Apolice> updateApolice(@PathVariable Long id,
			@Valid @RequestBody ApoliceDTO apoliceDTO){
		Optional<Apolice> apoliceOPTIONAL = apoliceService.findById(id);
		var apolice = new Apolice();
		BeanUtils.copyProperties(apoliceDTO, apolice);
		apolice.setId(apoliceOPTIONAL.get().getId());
		return ResponseEntity.ok().body(apoliceService.save(apolice));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> deleteApolice(@PathVariable Long id){
		Optional<Apolice> apoliceOPTIONAL = apoliceService.findById(id);
		apoliceService.delete(apoliceOPTIONAL.get());
		return ResponseEntity.status(HttpStatus.OK).body("Apolice deleted successfully");
		
	}

}
