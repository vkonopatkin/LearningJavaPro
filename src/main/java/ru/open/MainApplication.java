package ru.open;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.open.entities.Product;
import ru.open.entities.User;
import ru.open.service.ProductService;
import ru.open.service.UserService;

import java.util.List;

@ComponentScan
@SpringBootApplication
public class MainApplication {
	public static void main(String[] args) {

		System.out.println("Запуск");

		/* Для проверки сервисов доступа к БД
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainApplication.class);

		UserService userService = context.getBean(UserService.class);
		System.out.println("--- Поиск пользователя по Id");
		User user1 = userService.findUserById(1);
		System.out.println("найден " + user1);

		ProductService productService = context.getBean(ProductService.class);
		System.out.println("--- Поиск продукта по Id");
		Product product1 = productService.findProductById(1);
		System.out.println("найден " + product1);

		System.out.println("--- Поиск продуктов по userId");
		List<Product> products1 = productService.getAllProductsByUserId(1);
		System.out.println("найдены " + products1);
		 */

		ApplicationContext context = SpringApplication.run(MainApplication.class, args);

	}
}