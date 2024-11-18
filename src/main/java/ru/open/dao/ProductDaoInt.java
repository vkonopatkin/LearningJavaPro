package ru.open.dao;

import ru.open.entities.Product;
import java.util.List;

public interface ProductDaoInt {
	Product findProductById(long id);
	List<Product> getAllProductsByUserId(long userId);
}
