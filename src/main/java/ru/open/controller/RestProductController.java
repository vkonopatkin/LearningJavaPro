package ru.open.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.open.dto.ProductDto;
import ru.open.entities.Payment;
import ru.open.entities.Product;
import ru.open.entities.User;
import ru.open.service.ProductService;
import ru.open.service.UserService;
import java.util.List;

@RestController
@RequestMapping(value="/product") // Начало запроса в адресе (localhist/product/...)
public class RestProductController {

	@Autowired
	ProductService productService;
	@Autowired
	UserService userService;

	@GetMapping("/hello")
	public String hello(){
		return "hello";
	}

	@GetMapping("/findUserById")
	public ResponseEntity<Object> findUserById(@RequestParam("id") long id){
		User user = userService.findUserById(id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@GetMapping("/getAllUsers")
	public ResponseEntity<Object> getAllUsers(){
		List<User> users = userService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@GetMapping("/findProductById")
	public ResponseEntity<Object> findProductById(@RequestParam("id") long id){
		Product product = productService.findProductById(id);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@GetMapping("/getAllProductsByUserId")
	public ResponseEntity<Object> getAllProductsByUserId(@RequestParam("userId") User userId){
		List<ProductDto> productsDto = productService.getAllProductsByUserId(userId);
		return new ResponseEntity<>(productsDto, HttpStatus.OK);
	}

	@PostMapping("/updateProductBalance")
	// здесь можно было бы просто протянуть id и сумму из RestPaymentController, но сделал payment для разнообразия
	public ResponseEntity<Object>updateProductBalance(@RequestBody Payment payment){
		Double newBalance = productService.updateProductBalance(payment.getProductId(), payment.getBalanceDelta());
		return new ResponseEntity<>("Новый баланс = " + newBalance, HttpStatus.OK);
	}

}
