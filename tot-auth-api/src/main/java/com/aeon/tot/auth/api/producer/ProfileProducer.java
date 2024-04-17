package com.aeon.tot.auth.api.producer;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import com.aeon.tot.auth.api.config.ProfileProducerConfig;
import com.aeon.tot.auth.api.dto.BasicRegistrationRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

@Component
public class ProfileProducer {

	private AmqpTemplate amqpTemplate;
	
	public ProfileProducer(AmqpTemplate amqpTemplate) {
		this.amqpTemplate = amqpTemplate;
	}
	
	public void createProfile(BasicRegistrationRequest req) throws JsonProcessingException, AmqpException {
		amqpTemplate.convertAndSend(ProfileProducerConfig.EXCHANGE_NAME,
				ProfileProducerConfig.ROUTING_KEY, req);
	}
}
