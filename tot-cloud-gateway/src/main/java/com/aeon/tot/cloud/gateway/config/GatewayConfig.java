package com.aeon.tot.cloud.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
	
    @Autowired
    AuthenticationFilter filter;
	
	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("tot-profile-api", r -> r.path("/tot-profile-api/**")
						.filters(f -> f.stripPrefix(1)
								.filter(filter))
						.uri("lb://tot-profile-api"))
				.route("tot-auth-api", r -> r.path("/tot-auth-api/**")
						.filters(f -> f.stripPrefix(1))
						.uri("lb://tot-auth-api"))
				.build();
	}
}
