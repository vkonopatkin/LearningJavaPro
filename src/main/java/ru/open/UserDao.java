package ru.open;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDao implements UserDaoInt {

	private final DataSource dataSource;

	public UserDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@SneakyThrows
	public void insertUser(long id, String username){
		Connection connection = dataSource.getConnection();
		String sql = "insert into vk_jp4_users (id, username) values(?, ?)";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setLong(1, id);
		ps.setString(2, username);
		ps.executeUpdate();
		connection.close();
	}
	@SneakyThrows
	public void updateUser(long id, String username){
		Connection connection = dataSource.getConnection();
		String sql = "update vk_jp4_users set username = ? where id = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, username);
		ps.setLong(2, id);
		ps.executeUpdate();
		connection.close();
	}
	@SneakyThrows
	public void deleteUser(long id){
		Connection connection = dataSource.getConnection();
		String sql = "delete from vk_jp4_users where id = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setLong(1, id);
		ps.executeUpdate();
		connection.close();
	}
	@SneakyThrows
	public User findUserById(long id){
		User user = null;
		Connection connection = dataSource.getConnection();
		String sql = "select id, username from vk_jp4_users where id = ?";
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
		String sql = "select id, username from vk_jp4_users";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet resultSet = ps.executeQuery();
		while(resultSet.next()){
			allUsers.add(new User(resultSet.getLong(1), resultSet.getString(2)));
		}
		connection.close();
		return allUsers;
	}

}
