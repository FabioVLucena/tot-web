package com.aeon.tot.cloud.gateway.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import feign.Headers;

@Component
@FeignClient(name = "tot-auth-api", path = "/api/v1")
public interface AuthFeignClient {

	@GetMapping("/token/validate")
	@Headers("Authorization: Bearer {token}")
	ResponseEntity<String> validate(@RequestHeader("token") String token);
	
}
