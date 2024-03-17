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
import com.wamk.carInsurence.dtos.ClientDTO;
import com.wamk.carInsurence.entities.Client;
import com.wamk.carInsurence.entities.repositories.ApoliceRepository;
import com.wamk.carInsurence.entities.repositories.ClientRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
class ClientControllerTest {

	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	ApoliceRepository apoliceRepository;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	MockMvc mockMvc;

	Client client = new Client(null, "Wilson", "Rua das Flores");

	@BeforeEach
	void setup() {
		apoliceRepository.deleteAll();
		clientRepository.deleteAll();
	}
	
	@Test
	@DisplayName("Should Save The Client The Student Successfully")
	void saveCase01() throws Exception{
		assertEquals(0, clientRepository.count());
		
		String jsonRequest = objectMapper.writeValueAsString(new ClientDTO(client));
		
		mockMvc.perform(post("/clients")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.name", equalTo("Wilson")))
				.andExpect(jsonPath("$.address", equalTo("Rua das Flores")));
		
		assertEquals(1, clientRepository.count());
	}

	@Test
	@DisplayName("Should Fetch A List Of Clients Successfully")
	void findAllCase01() throws Exception{
		clientRepository.save(client);
		
		mockMvc.perform(get("/clients"))
				.andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("Should Find The Student From The Id Successfully")
	void findByIdCase01() throws Exception{
		clientRepository.save(client);
		
		Long id = clientRepository.findAll().get(0).getId();
		
		mockMvc.perform(get("/clients/{id}", id))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", equalTo("Wilson")))
				.andExpect(jsonPath("$.address", equalTo("Rua das Flores")));
	}
	
	@Test
	@Transactional
	@DisplayName("Should Update The Client The Student Successfully")
	void updateCase01() throws Exception{
		clientRepository.save(client);
		client.setName("Pedro");
		
		String jsonRequest = objectMapper.writeValueAsString(new ClientDTO(client));
		
		Long id = clientRepository.findAll().get(0).getId();
		
		mockMvc.perform(put("/clients/{id}", id)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", equalTo("Pedro")));
	}
	
	@Test
	@DisplayName("Should Delete The Client Successfully")
	void deleteCase01() throws Exception{
		clientRepository.save(client);
		assertEquals(1, clientRepository.count());
		
		Long id = clientRepository.findAll().get(0).getId();
		
		mockMvc.perform(delete("/clients/{id}", id))
				.andExpect(status().isNoContent());
		
		assertEquals(0, clientRepository.count());
	}
}
