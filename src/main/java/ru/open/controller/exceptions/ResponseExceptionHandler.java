package ru.open.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import ru.open.service.exceptions.Ex400BadRequest;
import ru.open.service.exceptions.Ex500InternalError;

import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
public class ResponseExceptionHandler {

	// Для перехвата ошибок при вызове сервиса напрямую
	@ExceptionHandler(Ex400BadRequest.class)
	public ResponseEntity handleBadRequest(Ex400BadRequest ex){
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(Ex500InternalError.class)
	public ResponseEntity handleIntError(Ex500InternalError ex){
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// Для перехвата ошибок при вызове другого REST сервиса в платежном сервисе (передача сообщения об ошибке сквозняком в вызывающий сервис)
	// при использовании WebClient
	@ExceptionHandler(WebClientResponseException.class)
	public ResponseEntity handleWebClientBadRequest(WebClientResponseException ex){
		return new ResponseEntity<>(ex.getResponseBodyAsString(), ex.getStatusCode());
	}

	// Все остальные, результат выдаём в виде простого текста с printStackTrace
	@ExceptionHandler(Exception.class)
	public ResponseEntity handleOthers(Exception ex){
		StringWriter sw = new StringWriter();
		ex.printStackTrace(new PrintWriter(sw));
		String stackTrace = "Необработанное исключение:\n" + sw.toString();
		return new ResponseEntity<>(stackTrace, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
