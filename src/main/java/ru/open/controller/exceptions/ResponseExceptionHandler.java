package ru.open.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import ru.open.service.exceptions.Ex400BadRequest;
import ru.open.service.exceptions.Ex404NotFound;

import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
public class ResponseExceptionHandler {

	// Для перехвата ошибок при вызове сервиса напрямую
	@ExceptionHandler(Ex404NotFound.class)
	public ResponseEntity handleNotFound(Ex404NotFound ex){
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(Ex400BadRequest.class)
	public ResponseEntity handleBadRequest(Ex400BadRequest ex){
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	// Для перехвата ошибок при вызове другого REST сервиса в платежном сервисе (передача сообщения об ошибке сквозняком в вызывающий сервис)
	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity handleHttpBadRequest(HttpClientErrorException ex){
		return new ResponseEntity<>(ex.getResponseBodyAsString(), ex.getStatusCode());
	}

	// Все остальные, результат выдаём в виде простого текста с printStackTrace
	@ExceptionHandler(Exception.class)
		public ResponseEntity handleOthers(Exception ex){
			StringWriter sw = new StringWriter();
			ex.printStackTrace(new PrintWriter(sw));
			String stackTrace = sw.toString();
			return new ResponseEntity<>(stackTrace, HttpStatus.INTERNAL_SERVER_ERROR);
		}

}
