package ru.open.controller;

import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import ru.open.entities.Payment;
import ru.open.service.exceptions.Ex400BadRequest;

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

}
