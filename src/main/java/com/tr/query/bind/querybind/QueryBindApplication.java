package com.tr.query.bind.querybind;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class QueryBindApplication {

	public static void main(String[] args) {
		SpringApplication.run(QueryBindApplication.class, args);
	}
}
