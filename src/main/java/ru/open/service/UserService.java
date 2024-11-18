package ru.open.service;

import org.springframework.stereotype.Service;
import ru.open.dao.UserDaoInt;
import ru.open.entities.User;

import java.util.List;

@Service
public class UserService {

	private final UserDaoInt userDao;

	public UserService(UserDaoInt userDao) {
		this.userDao = userDao;
	}
	public User findUserById(long id) {
		return userDao.findUserById(id);
	}
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

}
