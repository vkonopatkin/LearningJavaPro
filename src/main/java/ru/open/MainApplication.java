package ru.open;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan
@SpringBootApplication
@EnableScheduling
public class MainApplication {
	public static void main(String[] args) {

		System.out.println("Запуск");
		ApplicationContext context = SpringApplication.run(MainApplication.class, args);

	}
}