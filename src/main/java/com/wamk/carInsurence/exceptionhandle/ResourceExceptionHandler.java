package com.wamk.carInsurence.exceptionhandle;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.wamk.carInsurence.exceptionhandle.exceptions.EntityNotFoundException;
import com.wamk.carInsurence.exceptionhandle.exceptions.ExistingPlateException;

@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler{

	@Autowired
	private MessageSource messageSource;
	
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, 
			HttpHeaders headers, HttpStatusCode status, WebRequest request){
		List<Campo> campos = new ArrayList<>();
		
		for(ObjectError error : ex.getBindingResult().getAllErrors()) {
			String name = ((FieldError) error).getField();
			String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			
			campos.add(new Campo(name, message));
		}
		
		Problema problema = new Problema();
		problema.setStatus(status.value());
		problema.setDataHora(OffsetDateTime.now());
		problema.setTitulo("Um ou mais campos estão inválidos! Preencha-os corretamente");
		problema.setCampos(campos);
		
		return handleExceptionInternal(ex, problema, headers, status, request);
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Problema> entityNotFoundException() {
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		Problema problema = new Problema();
		problema.setStatus(status.value());
		problema.setDataHora(OffsetDateTime.now());
		problema.setTitulo("Um ou mais campos estão inválidos! Preencha-os corretamente");
		
		return ResponseEntity.status(status).body(problema);
	}
	
	@ExceptionHandler(ExistingPlateException.class)
	public ResponseEntity<Problema> existingPlateException() {
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		Problema problema = new Problema();
		problema.setStatus(status.value());
		problema.setDataHora(OffsetDateTime.now());
		problema.setTitulo("Esta Placa já foi cadastrada!");
		
		return ResponseEntity.status(status).body(problema);
	}
}
