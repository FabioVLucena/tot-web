package com.aeon.tot.profile.api.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aeon.tot.profile.api.bo.ProfileBO;
import com.aeon.tot.profile.api.dto.BasicRegistrationRequest;

@Component
public class ProfileConsumer {

	@Autowired
	private ProfileBO profileBO;
	
	private static Logger log = LoggerFactory.getLogger(ProfileConsumer.class);
	
	public static final String QUEUE_NAME = "QUEUE_PROFILE";
	public static final String EXCHANGE_NAME = "EXCHANGE_PROFILE";
	public static final String ROUTING_KEY = "CREATE_PROFILE";

	@RabbitListener(bindings = @QueueBinding(
			value = @Queue(QUEUE_NAME), 
			exchange = @Exchange(EXCHANGE_NAME),
			key = ROUTING_KEY
			))
	public void processMessage(final Message message, final BasicRegistrationRequest req) {
		try {
			profileBO.basicRegistration(req);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
}
