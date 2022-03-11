package ru.spring.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.spring.demo.Repository.Database;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		Database.connection();
		SpringApplication.run(DemoApplication.class, args);
	}

}
