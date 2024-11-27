package ru.open.dao;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import ru.open.entities.Product;
import ru.open.entities.ProductType;
import ru.open.service.exceptions.Ex400BadRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductDaoImpl implements ProductDao {

	private final DataSource dataSource;

	public ProductDaoImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@SneakyThrows
	public Product findProductById(long id){
		Product product = null;
		Connection connection = dataSource.getConnection();
		String sql = "select id, productname, accnumber, balance, producttype, userid int8 from vk_jp7_products where id = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setLong(1, id);
		ProductType prodTp;
		ResultSet resultSet = ps.executeQuery();
		if(resultSet.next()){
			try{
				prodTp = ProductType.valueOf(resultSet.getString(5));
			}
			catch(IllegalArgumentException ex){
				throw new Ex400BadRequest("Некорректное значение типа продукта " + resultSet.getString(5));
			}
			product = new Product(
					resultSet.getLong(1),
					resultSet.getString(2),
					resultSet.getString(3),
					resultSet.getDouble(4),
					prodTp,
					resultSet.getLong(6)
			);
		}
		connection.close();
		return product;
	}
	@SneakyThrows
	public List<Product> getAllProductsByUserId(long userId){
		List<Product> allProducts = new ArrayList<>();
		Connection connection = dataSource.getConnection();
		String sql = "select id, productname, accnumber, balance, producttype, userid int8 from vk_jp7_products where userid = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setLong(1, userId);
		ProductType prodTp;
		ResultSet resultSet = ps.executeQuery();
		while(resultSet.next()){
			try{
				prodTp = ProductType.valueOf(resultSet.getString(5));
			}
			catch(IllegalArgumentException ex){
				throw new Ex400BadRequest("Некорректное значение типа продукта " + resultSet.getString(5) +
						" у продукта id = " + resultSet.getLong(1));
			}
			allProducts.add(new Product(
					resultSet.getLong(1),
					resultSet.getString(2),
					resultSet.getString(3),
					resultSet.getDouble(4),
					prodTp,
					resultSet.getLong(6)
					)
			);
		}
		connection.close();
		return allProducts;
	}

	@SneakyThrows
	public void updateProductBalance(long id, double balanceDelta){
		Connection connection = dataSource.getConnection();
		String sql = "update vk_jp7_products set balance = balance + ? where id = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setDouble(1, balanceDelta);
		ps.setLong(2, id);
		ps.executeUpdate();
		connection.close();
	}

}
