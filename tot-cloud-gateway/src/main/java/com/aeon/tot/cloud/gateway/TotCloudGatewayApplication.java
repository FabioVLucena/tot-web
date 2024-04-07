package com.aeon.tot.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class TotCloudGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(TotCloudGatewayApplication.class, args);
	}

}
