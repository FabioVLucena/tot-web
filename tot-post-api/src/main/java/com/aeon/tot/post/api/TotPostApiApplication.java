package com.aeon.tot.post.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class TotPostApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TotPostApiApplication.class, args);
	}

}
