package com.awards.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan({"com.awards.api.controller", 
	"com.awards.api.service", 
	"com.awards.api.config", 
	"com.awards.api.exception"})
@EntityScan("com.awards.api.model")
@EnableJpaRepositories("com.awards.api.repository")
@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}
