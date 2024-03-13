package com.wamk.carInsurence.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wamk.carInsurence.entities.Client;
import com.wamk.carInsurence.entities.repositories.ClientRepository;

@SpringBootTest
class ClientServiceTest {

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ClientService clientService;

	Client client = new Client(null, "Wilson", "Rua das Flores");

	@BeforeEach
	void setup() {
		clientRepository.deleteAll();
	}

	@Test
	@DisplayName("Should Save The Client The Student Successfully")
	void saveCase01() {
		assertEquals(0, clientRepository.count());

		Client clientSaved =  clientService.save(client);
		
		assertEquals("Wilson", clientSaved.getName());
		assertEquals("Rua das Flores", clientSaved.getAddress());
		
		assertEquals(1, clientRepository.count());
	}

	@Test
	@DisplayName("Should Fetch a List of Clients Successfully")
	void findAllCase01() {
		clientRepository.save(client);

		List<Client> list = clientService.getAll();
		
		assertEquals(list.size(), 1);
		assertEquals(list.size(), clientRepository.count());
	}

	@Test
	@DisplayName("Should Find The Client From The Id Successfully")
	void findByIdCase01() {
		clientRepository.save(client);

		Long id = clientRepository.findAll().get(0).getId();

		Client clientFinded = clientService.findById(id);
		
		assertEquals("Wilson", clientFinded.getName());
		assertEquals("Rua das Flores", clientFinded.getAddress());
	}

	@Test
	@DisplayName("Should Update The Client Successfully")
	void updateCase01() {
		clientRepository.save(client);
		client.setName("Pedro");
		
		Long id = clientRepository.findAll().get(0).getId();

		Client clientUpdated = clientService.update(client, id);
		
		assertEquals("Pedro", clientUpdated.getName());
		assertEquals(1, clientRepository.count());
	}

	@Test
	@DisplayName("Should Delete The Client Successfully")
	void deleteCase01() {
		clientRepository.save(client);
		assertEquals(1, clientRepository.count());
		
		Long id = clientRepository.findAll().get(0).getId();

		clientService.delete(id);

		assertEquals(0, clientRepository.count());
	}

}
