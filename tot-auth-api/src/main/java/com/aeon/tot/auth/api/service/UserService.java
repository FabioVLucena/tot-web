package com.aeon.tot.auth.api.service;

import org.springframework.stereotype.Service;

import com.aeon.tot.auth.api.entity.User;
import com.aeon.tot.auth.api.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public User getUserById(Long id) {
		User user = userRepository.findById(id).orElse(null);
		
		return user;
	}
	
	public User getUserByUsername(String username) {
		User user = userRepository.findByUsername(username).orElse(null);
		
		return user;
	}
}
