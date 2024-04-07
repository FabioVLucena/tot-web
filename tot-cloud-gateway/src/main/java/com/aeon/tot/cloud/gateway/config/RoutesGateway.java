package com.aeon.tot.cloud.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.aeon.tot.cloud.gateway.feignclient.AuthFeignClient;

@Component
public class RoutesGateway {

	private AuthFeignClient authClient;
	
	public RoutesGateway(AuthFeignClient authClient) {
		this.authClient = authClient;
	}
	
	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("tot-profile-api", r -> r.path("/tot-profile-api/**")
						.filters(f -> f.stripPrefix(1)
								       .filter(new SecurityFilter(this.authClient)))
						.uri("lb://tot-profile-api"))
				.build();
	}
}
