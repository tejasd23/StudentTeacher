package com.example.StudDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class StudDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudDemoApplication.class, args);
	}

}
