package com.aeon.tot.auth.api.bo;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.aeon.tot.auth.api.entity.User;
import com.aeon.tot.auth.api.repository.UserRepository;
import com.aeon.tot.auth.api.service.UserService;

@Component
public class UserBO {
	
	private UserService userService;
	private UserRepository userRepository;

	public UserBO(UserService userService, UserRepository userRepository) {
		this.userService = userService;
		this.userRepository = userRepository;
	}
	
	public User create(String username, String password) throws Exception {
		validateUsernameInUse(username);

		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setCreatedAt(LocalDate.now());
		
		return userRepository.save(user);
	}

	private void validateUsernameInUse(String username) throws Exception {
		User user = this.userService.getUserByUsername(username);
		
		if (user != null)
			throw new Exception("username in use!");
	}
}
