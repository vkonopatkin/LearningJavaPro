package ru.open.dao;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import ru.open.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDaoImpl implements UserDao {

	private final DataSource dataSource;

	public UserDaoImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@SneakyThrows
	public User findUserById(long id){
		User user = null;
		Connection connection = dataSource.getConnection();
		String sql = "select id, username from vk_jp5_users where id = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setLong(1, id);
		ResultSet resultSet = ps.executeQuery();
		if(resultSet.next()){
			user = new User(resultSet.getLong(1), resultSet.getString(2));
		}
		connection.close();
		return user;
	}
	@SneakyThrows
	public List<User> getAllUsers(){
		List<User> allUsers = new ArrayList<>();
		Connection connection = dataSource.getConnection();
		String sql = "select id, username from vk_jp5_users";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet resultSet = ps.executeQuery();
		while(resultSet.next()){
			allUsers.add(new User(resultSet.getLong(1), resultSet.getString(2)));
		}
		connection.close();
		return allUsers;
	}

}
