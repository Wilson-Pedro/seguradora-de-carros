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

import com.wamk.carInsurence.dtos.AcidentDTO;
import com.wamk.carInsurence.entities.Acident;
import com.wamk.carInsurence.services.AcidentService;

@RestController
@RequestMapping("/acidents")
public class AcidentController {

	@Autowired
	private AcidentService acidentService;
	
	@PostMapping
	public ResponseEntity<Acident> addAcident(@RequestBody AcidentDTO acidentDTO){
		var acident = new Acident();
		BeanUtils.copyProperties(acidentDTO, acident);
		return ResponseEntity.status(HttpStatus.CREATED).body(acidentService.save(acident));
	}
	
	@GetMapping
	public ResponseEntity<List<Acident>> findAll(){
		List<Acident> list = acidentService.findAll();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Acident> findById(@PathVariable Long id){
		Optional<Acident> acidentOPTIONAL = acidentService.findById(id);
		return ResponseEntity.ok().body(acidentOPTIONAL.get());
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Acident> updateAcident(@RequestBody AcidentDTO acidentDTO, 
			@PathVariable Long id){
		
		Optional<Acident> acidentOPTIONAL = acidentService.findById(id);
		var acident = new Acident();
		BeanUtils.copyProperties(acidentDTO, acident);
		acident.setId(acidentOPTIONAL.get().getId());
		return ResponseEntity.ok(acidentService.save(acident));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> deleteAcident(@PathVariable Long id){
		Optional<Acident> acidentOPTIONAL = acidentService.findById(id);
		acidentService.delete(acidentOPTIONAL.get());
		return ResponseEntity.status(HttpStatus.OK).body("Acident deleted successfully");
	}
}
