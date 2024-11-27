package ru.open.dao;

import ru.open.entities.Product;
import java.util.List;

public interface ProductDao {
	Product findProductById(long id);
	List<Product> getAllProductsByUserId(long userId);
	void updateProductBalance(long id, double balance);
}
