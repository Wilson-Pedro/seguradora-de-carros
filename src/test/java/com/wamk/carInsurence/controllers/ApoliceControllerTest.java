package com.wamk.carInsurence.controllers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wamk.carInsurence.dtos.ApoliceDTO;
import com.wamk.carInsurence.entities.Apolice;
import com.wamk.carInsurence.entities.Car;
import com.wamk.carInsurence.entities.Client;
import com.wamk.carInsurence.entities.repositories.AcidentRepository;
import com.wamk.carInsurence.entities.repositories.ApoliceRepository;
import com.wamk.carInsurence.entities.repositories.CarRepository;
import com.wamk.carInsurence.entities.repositories.ClientRepository;

@SpringBootTest
@AutoConfigureMockMvc
class ApoliceControllerTest {

	@Autowired
	ApoliceRepository apoliceRepository;

	@Autowired
	CarRepository carRepository;
	
	@Autowired
	AcidentRepository acidentRepository;

	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	MockMvc mockMvc;

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
	void saveCase01() throws Exception{
		assertEquals(0, apoliceRepository.count());
		
		String jsonRequest = objectMapper.writeValueAsString(new ApoliceDTO(500.0));
		
		mockMvc.perform(post("/apolices")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.value", equalTo(500.0)));
		
		assertEquals(1, apoliceRepository.count());
	}
	
	@Test
	@DisplayName("Should Fetch a List of Apolices Successfully")
	void findAllCase01() throws Exception {
		clientRepository.save(client);
		carRepository.save(car);
		apoliceRepository.save(apolice);

		mockMvc.perform(get("/apolices"))
			.andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("Should Find The Apolice From The Id Successfully")
	void findByIdCase01() throws Exception {
		clientRepository.save(client);
		carRepository.save(car);
		apoliceRepository.save(apolice);

		Long id = apoliceRepository.findAll().get(0).getId();

		mockMvc.perform(get("/apolices/{id}", id))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.value", equalTo(500.0)));
	}

	@Test
	@DisplayName("Should Update The Apolice Successfully")
	void updateCase01() throws Exception {
		clientRepository.save(client);
		carRepository.save(car);
		apoliceRepository.save(apolice);
		
		Long id = apoliceRepository.findAll().get(0).getId();

		String jsonRequest = objectMapper.writeValueAsString(new ApoliceDTO(300.0));
		
		mockMvc.perform(put("/apolices/{id}", id)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.value", equalTo(300.0)));
		
		assertEquals(1, apoliceRepository.count());
	}

	@Test
	@DisplayName("Should Delete The Apolice Successfully")
	void deleteCase01() throws Exception {
		clientRepository.save(client);
		carRepository.save(car);
		apoliceRepository.save(apolice);

		Long id = apoliceRepository.findAll().get(0).getId();

		mockMvc.perform(delete("/apolices/{id}", id))
			.andExpect(status().isNoContent());

		assertEquals(0, apoliceRepository.count());
	}
}
