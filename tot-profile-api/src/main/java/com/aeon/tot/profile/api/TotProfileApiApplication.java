package com.aeon.tot.profile.api;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class TotProfileApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TotProfileApiApplication.class, args);
	}
	
}
