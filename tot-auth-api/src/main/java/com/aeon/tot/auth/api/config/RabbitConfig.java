package com.aeon.tot.auth.api.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class RabbitConfig {

	@Bean
	public RabbitTemplate jsonRabbitTemplate(ConnectionFactory connectionFactory, ObjectMapper mapper) {
	    final var jsonRabbitTemplate = new RabbitTemplate(connectionFactory);
	    jsonRabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter(mapper));
	    return jsonRabbitTemplate;
	}
}
