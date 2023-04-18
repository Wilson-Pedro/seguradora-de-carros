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

import com.wamk.carInsurence.dtos.CarDTO;
import com.wamk.carInsurence.entities.Car;
import com.wamk.carInsurence.services.CarService;

@RestController
@RequestMapping("/cars")
public class CarController {

	@Autowired
	private CarService carService;
	
	@PostMapping
	public ResponseEntity<Car> addCar(@RequestBody CarDTO carDTO){
		var car = new Car();
		BeanUtils.copyProperties(carDTO, car);
		return ResponseEntity.status(HttpStatus.CREATED).body(carService.save(car));
	}
	
	@GetMapping
	public ResponseEntity<List<Car>> findAll(){
		List<Car> list = carService.findAll();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Car> findById(@PathVariable Long id){
		Optional<Car> carOPTIONAL = carService.findById(id);
		return ResponseEntity.ok(carOPTIONAL.get());
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Car> updateCar(@PathVariable Long id, 
			@RequestBody CarDTO carDTO){
		
		Optional<Car> carOPTIONAL = carService.findById(id);
		var car = new Car();
		BeanUtils.copyProperties(carDTO, car);
		car.setId(carOPTIONAL.get().getId());
		return ResponseEntity.ok(carService.save(car));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> deleteCar(@PathVariable Long id){
		Optional<Car> carOPTIONAL = carService.findById(id);
		carService.delete(carOPTIONAL.get());
		return ResponseEntity.status(HttpStatus.OK).body("Car deleted successfully");
	}
}
