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

import com.wamk.carInsurence.dtos.CarDTO;
import com.wamk.carInsurence.entities.Car;
import com.wamk.carInsurence.services.CarService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cars")
public class CarController {

	@Autowired
	private CarService carService;
	
	@PostMapping
	public ResponseEntity<Car> addCar(@Valid @RequestBody CarDTO carDTO){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(carService.save(new Car(carDTO)));
	}
	
	@GetMapping
	public ResponseEntity<List<CarDTO>> findAll(){
		List<Car> list = carService.findAll();
		List<CarDTO> listDto = list.stream().map(x -> new CarDTO(x)).toList();
		return ResponseEntity.ok(listDto);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Car> findById(@PathVariable Long id){
		return ResponseEntity.ok(carService.findById(id));
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Car> updateCar(@Valid @PathVariable Long id, 
			@RequestBody CarDTO carDTO){
		
		Car carUpdate = carService.update(new Car(carDTO), id);
		return ResponseEntity.ok(carUpdate);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteCar(@PathVariable Long id){
		carService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
