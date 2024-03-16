package com.wamk.carInsurence.exceptions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wamk.carInsurence.exceptionhandle.exceptions.EntityNotFoundException;
import com.wamk.carInsurence.services.ClientService;

@SpringBootTest
class ClientExceptions {
	
	@Autowired
	ClientService clientService;

	@Test
	@DisplayName("EntityNotFoundException when triying to Find The Client")
	void entityNotFoundExceptionCase01() {
		
		assertThrows(EntityNotFoundException.class, () -> clientService.findById(70L));
	}
	
	@Test
	@DisplayName("EntityNotFoundException when triying to Delete The Client")
	void entityNotFoundExceptionCase02() {
		
		assertThrows(EntityNotFoundException.class, () -> clientService.delete(70L));
	}
}
