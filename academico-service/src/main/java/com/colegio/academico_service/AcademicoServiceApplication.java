package com.colegio.academico_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AcademicoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcademicoServiceApplication.class, args);
	}
}