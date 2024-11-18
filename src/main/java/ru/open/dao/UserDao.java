package ru.open.dao;

import ru.open.entities.User;
import java.util.List;

public interface UserDao {
	User findUserById(long id);
	List<User> getAllUsers();
}
