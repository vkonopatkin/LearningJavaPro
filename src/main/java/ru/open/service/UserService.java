package ru.open.service;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import ru.open.dao.UserDao;
import ru.open.entities.User;
import ru.open.service.exceptions.Ex404NotFound;

import java.util.List;

@Service
public class UserService {

	private final UserDao userDao;

	public UserService(UserDao userDao) {
		this.userDao = userDao;
	}
	@SneakyThrows
	public User findUserById(long id) {
		User user = userDao.findUserById(id);
		if(user == null){
			throw new Ex404NotFound("Не найден клиент id = " + id);
		}
		return user;
	}
	@SneakyThrows
	public List<User> getAllUsers() {
		List<User> allUsers = userDao.getAllUsers();
		if(allUsers.isEmpty()){
			throw new Ex404NotFound("Список клиентов пуст");
		}
		return allUsers;
	}

}
