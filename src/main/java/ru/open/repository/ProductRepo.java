package ru.open.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.open.entities.Product;
import ru.open.entities.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
	List<Product> findByUserId(User userId);
	Optional<Product> findById(long id);
}
