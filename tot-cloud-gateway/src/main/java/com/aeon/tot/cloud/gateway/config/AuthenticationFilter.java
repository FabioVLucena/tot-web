package com.aeon.tot.cloud.gateway.config;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter implements GatewayFilter {

	private final WebClient webClient;
	private static final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);

	@Autowired
	public AuthenticationFilter(WebClient.Builder webClientBuilder, ReactorLoadBalancerExchangeFilterFunction lbFunction) {
		this.webClient = webClientBuilder.filter(lbFunction)
				.baseUrl("http://tot-auth-api/api/v1")
				.build();
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		ServerHttpRequest request = exchange.getRequest();
		HttpHeaders headers = request.getHeaders();

		String authHeader = headers.getFirst(HttpHeaders.AUTHORIZATION);

		if (StringUtils.isEmpty(authHeader) || 
				!StringUtils.startsWith(authHeader, "Bearer ")) {
			exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
			return exchange.getResponse().setComplete();
		}

		return webClient.get()
				.uri("/token/validate")
				.headers(httpHeaders -> httpHeaders.addAll(headers))
				.retrieve()
				.bodyToMono(String.class)
				.flatMap(responseBody -> {
					HttpStatusCode responseStatus = exchange.getResponse().getStatusCode();
					if (responseStatus.is2xxSuccessful()) {
						logger.info("Resposta da API: {}", responseBody);
						return chain.filter(exchange);
					} else {
						logger.error("Status de resposta não foi bem-sucedido: {}", responseStatus);
						exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
						return exchange.getResponse().setComplete();
					}
				})
				.switchIfEmpty(chain.filter(exchange));
	}
}
