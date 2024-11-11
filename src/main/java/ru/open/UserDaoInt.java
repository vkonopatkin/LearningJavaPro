package ru.open;

import java.util.List;

public interface UserDaoInt {
	void insertUser(long id, String username);
	void updateUser(long id, String username);
	void deleteUser(long id);
	User findUserById(long id);
	List<User> getAllUsers();
}
