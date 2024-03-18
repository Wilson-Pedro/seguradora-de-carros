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
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wamk.carInsurence.dtos.CarDTO;
import com.wamk.carInsurence.entities.Car;
import com.wamk.carInsurence.entities.repositories.CarRepository;
import com.wamk.carInsurence.services.CarService;

@SpringBootTest
@AutoConfigureMockMvc
class CarControllerTest {
	
	@Autowired
	CarRepository carRepository;
	
	@Autowired
	CarService carService;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	MockMvc mockMvc;
	
	Car car = new Car(null, "1234567", "JHG-1477", "UNO");
	
	@BeforeEach
	void setup() {
		carRepository.deleteAll();
	}

	@Test
	@DisplayName("Shoul Save The Car Successfully")
	void saveCase01() throws Exception {
		assertEquals(0, carRepository.count());
		
		String jsonRequest = objectMapper.writeValueAsString(new CarDTO(car));
		
		mockMvc.perform(post("/cars")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.register", equalTo("1234567")))
				.andExpect(jsonPath("$.plate", equalTo("JHG-1477")))
				.andExpect(jsonPath("$.brand", equalTo("UNO")));
		
		assertEquals(1, carRepository.count());
	}
	
	@Test
	@DisplayName("Should Fetch a List of Cars Successfully")
	void findAllCase01() throws Exception {
		carRepository.save(car);

		mockMvc.perform(get("/cars"))
				.andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("Should Find The Car From The Id Successfully")
	void findByIdCase01() throws Exception {
		carRepository.save(car);

		Long id = carRepository.findAll().get(0).getId();

		mockMvc.perform(get("/cars/{id}", id))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.register", equalTo("1234567")))
				.andExpect(jsonPath("$.plate", equalTo("JHG-1477")))
				.andExpect(jsonPath("$.brand", equalTo("UNO")));
	}

	@Test
	@Transactional
	@DisplayName("Should Update The Car Successfully")
	void updateCase01() throws Exception {
		carRepository.save(car);
		car.setRegister("67890");
		
		Long id = carRepository.findAll().get(0).getId();

		String jsonRequest = objectMapper.writeValueAsString(new CarDTO(car));
		
		mockMvc.perform(put("/cars/{id}", id)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.register", equalTo("67890")));
	}
	
	@Test
	@DisplayName("Should Delete The Car Successfully")
	void deleteCase01() throws Exception {
		carRepository.save(car);
		assertEquals(1, carRepository.count());
		
		Long id = carRepository.findAll().get(0).getId();

		mockMvc.perform(delete("/cars/{id}", id))
				.andExpect(status().isNoContent());

		assertEquals(0, carRepository.count());
	}
}
