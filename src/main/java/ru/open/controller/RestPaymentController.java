package ru.open.controller;

import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.open.entities.Payment;
import ru.open.service.exceptions.Ex400BadRequest;
/*
4 сервиса:
 - получение списка продуктов клиента
 - получение продукта по id
 - пополнение баланса продукта
 - списание баланса продукта
с использованием разных способов обращения к другому REST сервису:
 - RestTemplate
 - RestClient
 - WebClient
*/
@RestController
@RequestMapping(value="/payment/v1") // Начало запроса в адресе (localhist/payment/v1/...)
public class RestPaymentController {

	private final String productUrl = "http://localhost:8080/product";

	// RestTemplate --------------------------------
	@GetMapping("/getAllProductsByUserIdRT") // получение списка продуктов клиента через RestTemplate
	public ResponseEntity<Object> getAllProductsByUserIdRT(@RequestParam("userId") int userId){
		ResponseEntity response;
		RestTemplate template = new RestTemplate();
		response = template.getForEntity(productUrl + "/getAllProductsByUserId?userId=" + userId, Object.class);
		return response;
	}
	@GetMapping("/findProductByIdRT") // получение продукта по id через RestTemplate
	public ResponseEntity<Object> getProductByIdRT(@RequestParam("id") int id){
		ResponseEntity response;
		RestTemplate template = new RestTemplate();
		response = template.getForEntity(productUrl + "/findProductById?id=" + id, Object.class);
		return response;
	}
	@SneakyThrows
	@PostMapping("/creditBalanceRT") // пополнение баланса продукта через RestTemplate
	public ResponseEntity<String> creditBalanceRT(@RequestParam("id") int id, @RequestParam("credit") double credit){
		if(credit < 0){
			throw new Ex400BadRequest("Сумма пополнения должна быть положительной!");
		}
		ResponseEntity response;
		RestTemplate template = new RestTemplate();
		response = template.postForEntity(productUrl + "/updateProductBalance",  new Payment(id, credit), String.class);
		return response;
	}
	@SneakyThrows
	@PostMapping("/debetBalanceRT") // списание баланса продукта через RestTemplate
	public ResponseEntity<String> debetBalanceRT(@RequestParam("id") int id, @RequestParam("debet") double debet){
		if(debet < 0){
			throw new Ex400BadRequest("Сумма списания должна быть положительной!");
		}
		ResponseEntity response;
		RestTemplate template = new RestTemplate();
		response = template.postForEntity(productUrl + "/updateProductBalance",  new Payment(id, -debet), String.class);
		return response;
	}

	// RestClient --------------------------------
	@GetMapping("/getAllProductsByUserIdRC") // получение списка продуктов клиента через RestClient
	public ResponseEntity<Object> getAllProductsByUserIdRC(@RequestParam("userId") int userId){
		ResponseEntity response;
		RestClient client = RestClient.builder()
				.baseUrl(productUrl)
				.build();
		response = client.get()
				.uri("/getAllProductsByUserId?userId=" + userId)
				.retrieve()
				.toEntity(Object.class);
		return response;
	}
	@GetMapping("/findProductByIdRC") // получение продукта по id через  RestClient
	public ResponseEntity<Object> findProductByIdRC(@RequestParam("id") int id){
		ResponseEntity response;
		RestClient client = RestClient.builder()
				.baseUrl(productUrl)
				.build();
		response = client.get()
				.uri("/findProductById?id=" + id)
				.retrieve()
				.toEntity(Object.class);
		return response;
	}
	@SneakyThrows
	@PostMapping("/creditBalanceRC") // пополнение баланса продукта через RestClient
	public ResponseEntity<String> creditBalanceRC(@RequestParam("id") int id, @RequestParam("credit") double credit){
		if(credit < 0){
			throw new Ex400BadRequest("Сумма пополнения должна быть положительной!");
		}
		ResponseEntity response;
		RestClient client = RestClient.builder()
				.baseUrl(productUrl)
				.build();
		response = client.post()
				.uri("/updateProductBalance")
				.body(new Payment(id, credit))
				.retrieve()
				.toEntity(String.class);
		return response;
	}
	@SneakyThrows
	@PostMapping("/debetBalanceRC") // списание баланса продукта через RestClient
	public ResponseEntity<String> debetBalanceRC(@RequestParam("id") int id, @RequestParam("debet") double debet){
		if(debet < 0){
			throw new Ex400BadRequest("Сумма списания должна быть положительной!");
		}
		ResponseEntity response;
		RestClient client = RestClient.builder()
				.baseUrl(productUrl)
				.build();
		response = client.post()
				.uri("/updateProductBalance")
				.body(new Payment(id, -debet))
				.retrieve()
				.toEntity(String.class);
		return response;
	}

	// WebClient --------------------------------
	@GetMapping("/getAllProductsByUserIdWC") // получение списка продуктов клиента через WebClient
	public ResponseEntity<Object> getAllProductsByUserIdWC(@RequestParam("userId") int userId){
		ResponseEntity response;
		WebClient client = WebClient.builder()
				.baseUrl(productUrl)
				.build();
		Mono<Object> result = client.get()
				.uri("/getAllProductsByUserId?userId=" + userId)
				.retrieve()
				.bodyToMono(Object.class);
		response = new ResponseEntity<Object>(result.block(), HttpStatus.OK);
		return response;
	}
	@GetMapping("/findProductByIdWC") // получение продукта по id через WebClient
	public ResponseEntity<Object> findProductByIdWC(@RequestParam("id") int id){
		ResponseEntity response;
		WebClient client = WebClient.builder()
				.baseUrl(productUrl)
				.build();
		Mono<Object> result = client.get()
				.uri("/findProductById?id=" + id)
				.retrieve()
				.bodyToMono(Object.class);
		response = new ResponseEntity<Object>(result.block(), HttpStatus.OK);
		return response;
	}
	@SneakyThrows
	@PostMapping("/creditBalanceWC") // пополнение баланса продукта через WebClient
	public ResponseEntity<String> creditBalanceWC(@RequestParam("id") int id, @RequestParam("credit") double credit){
		if(credit < 0){
			throw new Ex400BadRequest("Сумма пополнения должна быть положительной!");
		}
		ResponseEntity response;
		WebClient client = WebClient.builder()
				.baseUrl(productUrl)
				.build();
		Mono<String> result = client.post()
				.uri("/updateProductBalance")
				.body(Mono.just(new Payment(id, credit)), Payment.class)
				.retrieve()
				.bodyToMono(String.class);
		response = new ResponseEntity<Object>(result.block(), HttpStatus.OK);
		return response;
	}
	@SneakyThrows
	@PostMapping("/debetBalanceWC") // списание баланса продукта через WebClient
	public ResponseEntity<String> debetBalanceWC(@RequestParam("id") int id, @RequestParam("debet") double debet){
		if(debet < 0){
			throw new Ex400BadRequest("Сумма списания должна быть положительной!");
		}
		ResponseEntity response;
		WebClient client = WebClient.builder()
				.baseUrl(productUrl)
				.build();
		Mono<String> result = client.post()
				.uri("/updateProductBalance")
				.body(Mono.just(new Payment(id, -debet)), Payment.class)
				.retrieve()
				.bodyToMono(String.class);
		response = new ResponseEntity<Object>(result.block(), HttpStatus.OK);
		return response;
	}

}
