package com.wamk.carInsurence.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wamk.carInsurence.entities.Car;
import com.wamk.carInsurence.entities.repositories.AcidentRepository;
import com.wamk.carInsurence.entities.repositories.ApoliceRepository;
import com.wamk.carInsurence.entities.repositories.CarRepository;
import com.wamk.carInsurence.entities.repositories.ClientRepository;

@SpringBootTest
class CarServiceTest {
	
	@Autowired
	CarRepository carRepository;
	
	@Autowired
	CarService carService;
	
	@Autowired
	ApoliceRepository apoliceRepository;
	
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	AcidentRepository acidentRepository;
	
	Car car = new Car(null, "1234567", "JHG-1477", "UNO");
	
	@BeforeEach
	void setup() {
		apoliceRepository.deleteAll();
		clientRepository.deleteAll();
		acidentRepository.deleteAll();
		carRepository.deleteAll();
	}

	@Test
	@DisplayName("Shoul Save The Car Successfully")
	void saveCase01() {
		assertEquals(0, carRepository.count());
		
		Car carSaved = carService.save(car);
		
		assertNotNull(carSaved.getId());
		assertEquals("1234567", carSaved.getRegister());
		assertEquals("JHG-1477", carSaved.getPlate());
		assertEquals("UNO", carSaved.getBrand());
		assertEquals(1, carRepository.count());
	}

	@Test
	@DisplayName("Should Fetch a List of Cars Successfully")
	void findAllCase01() {
		carRepository.save(car);

		List<Car> list = carService.findAll();
		
		assertEquals(list.size(), 1);
		assertEquals(list.size(), carRepository.count());
	}

	@Test
	@DisplayName("Should Find The Car From The Id Successfully")
	void findByIdCase01() {
		carRepository.save(car);

		Long id = carRepository.findAll().get(0).getId();

		Car carFinded = carService.findById(id);
		
		assertEquals("1234567", carFinded.getRegister());
		assertEquals("JHG-1477", carFinded.getPlate());
		assertEquals("UNO", carFinded.getBrand());
	}

	@Test
	@DisplayName("Should Update The Car Successfully")
	void updateCase01() {
		carRepository.save(car);
		car.setRegister("67890");
		
		Long id = carRepository.findAll().get(0).getId();

		Car carUpdated = carService.update(car, id);
		
		assertEquals("67890", carUpdated.getRegister());
		assertEquals(1, carRepository.count());
	}

	@Test
	@DisplayName("Should Delete The Car Successfully")
	void deleteCase01() {
		carRepository.save(car);
		assertEquals(1, carRepository.count());
		
		Long id = carRepository.findAll().get(0).getId();

		carService.delete(id);

		assertEquals(0, carRepository.count());
	}
}
