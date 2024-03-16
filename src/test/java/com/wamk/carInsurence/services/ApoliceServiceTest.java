package com.wamk.carInsurence.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wamk.carInsurence.entities.Apolice;
import com.wamk.carInsurence.entities.Car;
import com.wamk.carInsurence.entities.Client;
import com.wamk.carInsurence.entities.repositories.AcidentRepository;
import com.wamk.carInsurence.entities.repositories.ApoliceRepository;
import com.wamk.carInsurence.entities.repositories.CarRepository;
import com.wamk.carInsurence.entities.repositories.ClientRepository;

@SpringBootTest
class ApoliceServiceTest {

	@Autowired
	ApoliceRepository apoliceRepository;

	@Autowired
	ApoliceService apoliceService;

	@Autowired
	CarRepository carRepository;
	
	@Autowired
	AcidentRepository acidentRepository;

	@Autowired
	ClientRepository clientRepository;

	Client client = new Client(null, "Wilson", "Rua das Flores");

	Car car = new Car(null, "1234567", "JHG-1477", "UNO");

	Apolice apolice = new Apolice(null, 500.0, client, car); 


	@BeforeEach
	void setup() {
		apoliceRepository.deleteAll();
		clientRepository.deleteAll();
		acidentRepository.deleteAll();
		carRepository.deleteAll();
	}

	@Test
	@DisplayName("Shoul Save The Apolice Successfully")
	void saveCase01() {
		assertEquals(0, apoliceRepository.count());
		clientRepository.save(client);
		carRepository.save(car);

		Apolice apoliceSaved = apoliceService.save(apolice);

		assertNotNull(apoliceSaved.getId());
		assertEquals(500.0, apoliceSaved.getValue());
		assertEquals(1, apoliceRepository.count());
	}

	@Test
	@DisplayName("Should Fetch a List of Apolices Successfully")
	void findAllCase01() {
		clientRepository.save(client);
		carRepository.save(car);
		apoliceRepository.save(apolice);

		List<Apolice> list = apoliceService.findAll();
		
		assertEquals(list.size(), 1);
		assertEquals(list.size(), apoliceRepository.count());
	}

	@Test
	@DisplayName("Should Find The Apolice From The Id Successfully")
	void findByIdCase01() {
		clientRepository.save(client);
		carRepository.save(car);
		apoliceRepository.save(apolice);

		Long id = apoliceRepository.findAll().get(0).getId();

		Apolice apoliceFinded = apoliceService.findById(id);

		assertEquals(500.0, apoliceFinded.getValue());
	}

	@Test
	@DisplayName("Should Update The Apolice Successfully")
	void updateCase01() {
		clientRepository.save(client);
		carRepository.save(car);
		apoliceRepository.save(apolice);
		apolice.setValue(300.0);
		
		Long id = apoliceRepository.findAll().get(0).getId();

		Apolice apoliceUpdated = apoliceService.update(apolice, id);
		
		assertEquals(300.0, apoliceUpdated.getValue());
		assertEquals(1, apoliceRepository.count());
	}

	@Test
	@DisplayName("Should Delete The Apolice Successfully")
	void deleteCase01() {
		clientRepository.save(client);
		carRepository.save(car);
		apoliceRepository.save(apolice);

		Long id = apoliceRepository.findAll().get(0).getId();

		apoliceService.delete(id);

		assertEquals(0, apoliceRepository.count());
	}
}
