package com.aeon.tot.auth.api.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProfileProducerConfig {

	public static final String QUEUE_NAME = "QUEUE_PROFILE";
	public static final String EXCHANGE_NAME = "EXCHANGE_PROFILE";
	public static final String ROUTING_KEY = "CREATE_PROFILE";

	@Bean
	public Queue queue() {
		return QueueBuilder.durable(QUEUE_NAME).build();
	}
	
	@Bean
	public DirectExchange exchange() {
		return new DirectExchange(EXCHANGE_NAME);
	}
	
	@Bean
	public Binding binding() {
		return BindingBuilder.bind(queue())
				.to(exchange())
				.with(ROUTING_KEY);
	}
}
