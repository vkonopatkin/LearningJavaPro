package ru.open.controller;

import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.open.entities.Payment;
import ru.open.service.exceptions.Ex500InternalError;

// Сервис-заглушка, платеж без учета лимита, будет вызываться платежом RestPayLimitController с учетом лимита
// и возвращать или не возвращать ошибку
@RestController
@RequestMapping(value="/pay/v1") // Начало запроса в адресе (localhist/pay/v1/...)
public class RestPayController {

	@SneakyThrows
	@PostMapping("/payment")
	public ResponseEntity<String> sendPayment(@RequestBody Payment payment) {
		if(payment.getReturnError() != 0) {
			throw new Ex500InternalError("Платеж не выполнен. Ошибка платежного сервиса.");
		}
		return new ResponseEntity<>("Платеж на сумму " + payment.getPaymentSumma() + " успешно выполнен.", HttpStatus.OK);
	}

}
