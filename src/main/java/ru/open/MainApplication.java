package ru.open;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class MainApplication {
	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainApplication.class);

		UserService userService = context.getBean(UserService.class);

		System.out.println("--- Новые пользователи");
		User user1 = new User(1L, "User 1");
		User user2 = new User(2L, "User 2");
		userService.insertUser(user1);
		userService.insertUser(user2);
		System.out.println(userService.getAllUsers());

		System.out.println("--- Изменение пользователя");
		user1.setUsername("User 1 updated");
		userService.updateUser(user1);
		System.out.println(userService.getAllUsers());

		System.out.println("--- Поиск пользователя");
		User user1_2 = userService.findUserById(1);
		System.out.println("найден " + user1_2);

		System.out.println("--- Удаление пользователя 1");
		userService.deleteUser(user1);
		System.out.println(userService.getAllUsers());
		System.out.println("--- Удаление пользователя 2");
		userService.deleteUser(user2);
		System.out.println(userService.getAllUsers());

	}
}