package com.aeon.tot.auth.api.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aeon.tot.auth.api.dto.BasicRegistrationRequest;
import com.aeon.tot.auth.api.producer.ProfileProducer;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class ProfileFacade {

	private static Logger log = LoggerFactory.getLogger(ProfileFacade.class);
	
	private ProfileProducer producer;
	
	public ProfileFacade(ProfileProducer producer) {
		this.producer = producer;
	}
	
	public void basicRegistration(BasicRegistrationRequest req) {
		try {
			producer.createProfile(req);
			
			log.info("Processing request...");
		} catch (JsonProcessingException e) {
			log.error("An error occurred while registering the profile.." + e.getMessage());
		}
	}
}
