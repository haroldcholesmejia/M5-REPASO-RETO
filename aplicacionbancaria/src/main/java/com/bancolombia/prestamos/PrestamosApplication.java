package com.bancolombia.prestamos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.bancolombia.prestamos.repository")
public class PrestamosApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrestamosApplication.class, args);
	}

}
