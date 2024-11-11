package ru.open;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

	private final UserDaoInt userDao;

	public UserService(UserDaoInt userDao) {
		this.userDao = userDao;
	}
	public void insertUser(User user){
		userDao.insertUser(user.getId(), user.getUsername());
	}
	public void updateUser(User user){
		userDao.updateUser(user.getId(), user.getUsername());
	}
	public void deleteUser(User user){
		userDao.deleteUser(user.getId());
	}
	public User findUserById(long id) {
		return userDao.findUserById(id);
	}
	public List<User> getAllUsers(){
		return userDao.getAllUsers();
	}

}
