package ru.open;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import java.sql.Connection;

@Service
public class DataSource {
	private static HikariConfig config = new HikariConfig();
	private static HikariDataSource dataSource;
	static{
		config.setJdbcUrl("jdbc:postgresql://tst-zabbix005:5432/feoktistov_v");
		config.setUsername("orts");
		config.setPassword("orts");
		dataSource = new HikariDataSource(config);
	}

	@SneakyThrows
	public static Connection getConnection(){
		return dataSource.getConnection();
	}

}
