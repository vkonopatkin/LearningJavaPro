package ru.open.service;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import ru.open.dao.ProductDao;
import ru.open.entities.Product;
import ru.open.service.exceptions.Ex404NotFound;

import java.util.List;

@Service
public class ProductService {

	private final ProductDao productDao;

	public ProductService(ProductDao productDao) {
		this.productDao = productDao;
	}
	@SneakyThrows
	public Product findProductById(long id) {
		Product product = productDao.findProductById(id);
		if(product == null){
			throw new Ex404NotFound("Не найден продукт id = " + id);
		}
		return product;
	}
	@SneakyThrows
	public List<Product> getAllProductsByUserId(long userId) {
		List<Product> allProducts = productDao.getAllProductsByUserId(userId);
		if(allProducts.isEmpty()){
			throw new Ex404NotFound("Пустой список продуктов у пользователя userid = " + userId);
		}
		return allProducts;
	}

}
