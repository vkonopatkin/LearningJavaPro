package ru.open.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.open.entities.User;
import ru.open.repository.UserRepo;
import ru.open.service.exceptions.Ex404NotFound;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

	@Autowired
	UserRepo userRepo;
	@SneakyThrows
	public User findUserById(long id) {
		Optional<User> user = userRepo.findById(id);
		if(user.isEmpty()){
			throw new Ex404NotFound("Не найден клиент id = " + id);
		}
		return user.get();
	}
	@SneakyThrows
	public List<User> getAllUsers() {
		List<User> allUsers = userRepo.findAll();
		if(allUsers.isEmpty()){
			throw new Ex404NotFound("Список клиентов пуст");
		}
		return allUsers;
	}
}
