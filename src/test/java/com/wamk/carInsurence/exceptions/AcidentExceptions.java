package com.wamk.carInsurence.exceptions;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wamk.carInsurence.exceptionhandle.exceptions.EntityNotFoundException;
import com.wamk.carInsurence.services.AcidentService;

@SpringBootTest
class AcidentExceptions {
	
	@Autowired
	AcidentService acidentService;

	@Test
	@DisplayName("EntityNotFoundException when triying to Find The Acident")
	void entityNotFoundExceptionCase01() {
		
		assertThrows(EntityNotFoundException.class, () -> acidentService.findById(70L));
	}
	
	@Test
	@DisplayName("EntityNotFoundException when triying to Delete The Acident")
	void entityNotFoundExceptionCase02() {
		
		assertThrows(EntityNotFoundException.class, () -> acidentService.delete(70L));
	}
}
