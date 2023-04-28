package com.example.smd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SmdSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmdSpringApplication.class, args);
	}

}
