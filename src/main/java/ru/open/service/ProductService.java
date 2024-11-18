package ru.open.service;

import org.springframework.stereotype.Service;
import ru.open.dao.ProductDaoInt;
import ru.open.entities.Product;

import java.util.List;

@Service
public class ProductService {

	private final ProductDaoInt productDao;

	public ProductService(ProductDaoInt productDao) {
		this.productDao = productDao;
	}
	public Product findProductById(long id) {
		return productDao.findProductById(id);
	}
	public List<Product> getAllProductsByUserId(long userId) {
		return productDao.getAllProductsByUserId(userId);
	}

}
