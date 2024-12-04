package ru.open.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.open.dto.ProductDto;
import ru.open.entities.Product;
import ru.open.entities.User;
import ru.open.repository.ProductRepo;
import ru.open.service.exceptions.Ex400BadRequest;
import ru.open.service.exceptions.Ex404NotFound;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

	@Autowired
	ProductRepo productRepo;

	@SneakyThrows
	public Product findProductById(long id) {
		Optional<Product> productOpt = productRepo.findById(id);
		if(productOpt.isEmpty()){
			throw new Ex404NotFound("Не найден продукт id = " + id);
		}
		return productOpt.get();
	}
	@SneakyThrows
	public List<ProductDto> getAllProductsByUserId(User userId) { // ProductDto - чтобы выводить в результат запроса не всего пользователя, а только userid
		List<Product> allProducts = productRepo.findByUserId(userId);
		if(allProducts.isEmpty()){
			throw new Ex404NotFound("Пустой список продуктов у пользователя userid = " + userId);
		}
		List<ProductDto> allProductsDto = new ArrayList<>();
		Product product;
		for(int i = 0; i < allProducts.size(); i++){
			product = allProducts.get(i);
			allProductsDto.add(new ProductDto(
					product.getId(),
					product.getProductName(),
					product.getAccNumber(),
					product.getBalance(),
					product.getProductType().toString(),
					product.getUserId().getId()
			));
		}
		return allProductsDto;
	}
	@SneakyThrows
	public double updateProductBalance(long id, double balanceDelta){ // возвращаем баланс после апдейта
		Product product = findProductById(id);
		if(product.getBalance() + balanceDelta < 0){
			throw new Ex400BadRequest("Недостаточный баланс (" + product.getBalance() + " руб) для списания " + (-1 * balanceDelta) + " руб");
		}
		product.setBalance(product.getBalance() + balanceDelta);
		productRepo.save(product);
		return product.getBalance();
	}

}
