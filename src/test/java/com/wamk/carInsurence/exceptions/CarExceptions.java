package com.wamk.carInsurence.exceptions;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wamk.carInsurence.entities.Car;
import com.wamk.carInsurence.entities.repositories.CarRepository;
import com.wamk.carInsurence.exceptionhandle.exceptions.EntityNotFoundException;
import com.wamk.carInsurence.exceptionhandle.exceptions.ExistingPlateException;
import com.wamk.carInsurence.services.CarService;

@SpringBootTest
class CarExceptions {
	
	@Autowired
	CarRepository carRepository;
	
	@Autowired
	CarService carService;
	
	@BeforeEach
	void setup() {
		carRepository.deleteAll();
	}

	@Test
	@DisplayName("EntityNotFoundException when triying to Find The Car")
	void entityNotFoundExceptionCase01() {
		
		assertThrows(EntityNotFoundException.class, () -> carService.findById(70L));
	}
	
	@Test
	@DisplayName("EntityNotFoundException when triying to Delete The Car")
	void entityNotFoundExceptionCase02() {
		
		assertThrows(EntityNotFoundException.class, () -> carService.delete(70L));
	}
	
	@Test
	@DisplayName("ExistingPlateException when triying to Find The Car")
	void existingPlateExceptionCase01() {
		Car car = new Car(null, "1234567", "JHG-1477", "UNO");
		carRepository.save(car);
		
		assertThrows(ExistingPlateException.class, 
				() -> carService.save(car));
	}
}
