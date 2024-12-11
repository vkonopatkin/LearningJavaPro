package ru.open.controller;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.open.entities.Payment;
import ru.open.service.PayLimitService;

@RestController
@RequestMapping(value="/payLimit/v1") // Начало запроса в адресе (localhist/payLimit/v1/...)
@Transactional(rollbackFor = Exception.class) // rollback будет при выбрасывании любого потомка Exception
public class RestPayLimitController {

	@Autowired
	PayLimitService payLimitService;

	private final String productUrl = "http://localhost:8080/pay/v1";

	@SneakyThrows
	@PostMapping("/payment")
	public ResponseEntity<String> sendPaymentWC(@RequestBody Payment payment){

		ResponseEntity response;

		// Проверяем лимит и уменьшаем, если хватает
		double limit;
		try {
			limit = payLimitService.payLimit(payment); // Оставшийся лимит после уменьшения
		}
		catch(Exception ex){
			response = new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
			return response;
		}

		// Дёргаем внешний сервис платежа через WebClient (если будет ошибка, изменение лимита отроллбэчится по @Transactional)
		WebClient client = WebClient.builder()
				.baseUrl(productUrl)
				.build();
		Mono<String> result = client.post()
				.uri("/payment")
				.body(Mono.just(payment), Payment.class)
				.retrieve()
				.bodyToMono(String.class);

		response = new ResponseEntity<String>(result.block()  + " Оставшийса на сегодня лимит: " + limit + " руб.", HttpStatus.OK);
		return response;
	}

}
