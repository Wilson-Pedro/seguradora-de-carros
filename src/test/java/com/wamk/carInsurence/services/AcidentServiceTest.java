package com.wamk.carInsurence.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.wamk.carInsurence.entities.Acident;
import com.wamk.carInsurence.entities.Car;
import com.wamk.carInsurence.entities.repositories.AcidentRepository;
import com.wamk.carInsurence.entities.repositories.CarRepository;

@SpringBootTest
class AcidentServiceTest {

	@Autowired
	CarRepository carRepository;

	@Autowired
	AcidentRepository acidentRepository;

	@Autowired
	AcidentService acidentService;

	Car car = new Car(null, "1234567", "JHG-1477", "UNO");
	Acident acident = new Acident(null, Instant.now(), Instant.now(), "Av.Getúlio Vargas", car);


	@BeforeEach
	void setup() {
		acidentRepository.deleteAll();
		carRepository.deleteAll();
	}

	@Test
	@DisplayName("Shoul Save The Acident Successfully")
	void saveCase01() {
		assertEquals(0, acidentRepository.count());
		carRepository.save(car);

		Acident acidentSaved = acidentService.save(acident);

		assertNotNull(acidentSaved.getId());
		assertEquals("Av.Getúlio Vargas", acidentSaved.getLocal_acidente());
		assertEquals(1, acidentRepository.count());
	}

	@Test
	@DisplayName("Should Fetch a List of Acidents Successfully")
	void findAllCase01() {
		carRepository.save(car);
		acidentRepository.save(acident);

		List<Acident> list = acidentService.findAll();
		
		assertEquals(list.size(), 1);
		assertEquals(list.size(), acidentRepository.count());
	}

	@Test
	@DisplayName("Should Find The Acident From The Id Successfully")
	void findByIdCase01() {
		carRepository.save(car);
		acidentRepository.save(acident);

		Long id = acidentRepository.findAll().get(0).getId();

		Acident acidentFinded = acidentService.findById(id);
		
		assertEquals("Av.Getúlio Vargas", acidentFinded.getLocal_acidente());
	}

	@Test
	@DisplayName("Should Delete The Acident Successfully")
	@Transactional
	void deleteCase01() {
		carRepository.save(car);
		acidentRepository.save(acident);
		assertEquals(1, acidentRepository.count());

		Long id = acidentRepository.findAll().get(0).getId();

		acidentService.delete(id);

		assertEquals(0, acidentRepository.count());
	}
}
