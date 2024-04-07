package com.aeon.tot.cloud.gateway.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ServerWebExchange;

import com.aeon.tot.cloud.gateway.feignclient.AuthFeignClient;

import reactor.core.publisher.Mono;

public class SecurityFilter implements GatewayFilter {

	private AuthFeignClient authClient;
	
	public SecurityFilter(AuthFeignClient authClient) {
		this.authClient = authClient;
	}
	
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders headers = exchange.getRequest().getHeaders();
        
        String authHeader = headers.getFirst(HttpHeaders.AUTHORIZATION);
        
        if (StringUtils.isEmpty(authHeader) || 
        	!StringUtils.startsWith(authHeader, "Bearer ")) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        } else {
        	String jwt = authHeader.substring(7);
        	
        	ResponseEntity<String> res = this.authClient.validate(jwt);
        	HttpStatusCode status = res.getStatusCode();
        	Integer statusCode = status.value();

        	if (!statusCode.equals(HttpStatus.OK.value())) {
        		exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
        	}
        }
    	
        return chain.filter(exchange);
    }
}
