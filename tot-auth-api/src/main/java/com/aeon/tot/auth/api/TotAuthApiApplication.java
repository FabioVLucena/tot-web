package com.aeon.tot.auth.api;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;

@EnableRabbit
@SpringBootApplication
public class TotAuthApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TotAuthApiApplication.class, args);
	}

	@Bean 
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
	    return new BCryptPasswordEncoder(); 
	}
	
	@Bean 
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
}
