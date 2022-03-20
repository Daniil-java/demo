package ru.spring.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.spring.demo.Repository.Database;

import javax.sql.DataSource;

@SpringBootApplication
public class DemoApplication {
	@Autowired Environment env;

	public static void main(String[] args) {
		Database.connection();
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public DataSource dataSource() {
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("spring.datasource.driverClassName"));
		dataSource.setUrl(env.getProperty("spring.datasource.url"));
		dataSource.setUsername(env.getProperty("spring.datasource.user")); // дали если у тебя этого нет
		dataSource.setPassword(env.getProperty("spring.datasource.password")); // дали если у тебя этого нет
		return dataSource;
	}
}
