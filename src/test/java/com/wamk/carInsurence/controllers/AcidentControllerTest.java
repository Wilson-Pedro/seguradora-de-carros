package com.wamk.carInsurence.controllers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wamk.carInsurence.dtos.AcidentDTO;
import com.wamk.carInsurence.entities.Acident;
import com.wamk.carInsurence.entities.Car;
import com.wamk.carInsurence.entities.repositories.AcidentRepository;
import com.wamk.carInsurence.entities.repositories.CarRepository;
import com.wamk.carInsurence.services.AcidentService;

@SpringBootTest
@AutoConfigureMockMvc
class AcidentControllerTest {
	
	@Autowired
	CarRepository carRepository;

	@Autowired
	AcidentRepository acidentRepository;

	@Autowired
	AcidentService acidentService;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	MockMvc mockMvc;

	Car car = new Car(null, "1234567", "JHG-1477", "UNO");
	
	Acident acident = new Acident(null, Instant.now(), Instant.now(), "Av.Getúlio Vargas", car);


	@BeforeEach
	void setup() {
		acidentRepository.deleteAll();
		carRepository.deleteAll();
	}

	@Test
	@DisplayName("Shoul Save The Acident Successfully")
	void saveCase01() throws Exception {
		assertEquals(0, acidentRepository.count());
		carRepository.save(car);

		String jsonRequest = objectMapper.writeValueAsString(new AcidentDTO(acident));
		
		mockMvc.perform(post("/acidents")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.local_acidente", equalTo("Av.Getúlio Vargas")));

		assertEquals(1, acidentRepository.count());
	}

	@Test
	@DisplayName("Should Fetch a List of Acidents Successfully")
	void findAllCase01() throws Exception {
		carRepository.save(car);
		acidentRepository.save(acident);

		mockMvc.perform(get("/acidents"))
				.andExpect(status().isOk());

	}

	@Test
	@DisplayName("Should Find The Acident From The Id Successfully")
	void findByIdCase01() throws Exception {
		carRepository.save(car);
		acidentRepository.save(acident);

		Long id = acidentRepository.findAll().get(0).getId();

		mockMvc.perform(get("/acidents/{id}", id))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.local_acidente", equalTo("Av.Getúlio Vargas")));
	}

	@Test
	@DisplayName("Should Delete The Acident Successfully")
	@Transactional
	void deleteCase01() throws Exception {
		carRepository.save(car);
		acidentRepository.save(acident);
		assertEquals(1, acidentRepository.count());

		Long id = acidentRepository.findAll().get(0).getId();

		mockMvc.perform(delete("/acidents/{id}", id))
		.andExpect(status().isNoContent());

		assertEquals(0, acidentRepository.count());
	}
}
