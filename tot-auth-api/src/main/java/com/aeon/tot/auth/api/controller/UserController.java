package com.aeon.tot.auth.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aeon.tot.auth.api.dto.GetUserByIdResponse;
import com.aeon.tot.auth.api.entity.User;
import com.aeon.tot.auth.api.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<GetUserByIdResponse> getUserById(@PathVariable Long userId) {
		User user = this.userService.getUserById(userId);

		if (user == null) user = new User();
		
		GetUserByIdResponse res = GetUserByIdResponse.convert(user);
		
		return ResponseEntity.status(HttpStatus.OK).body(res);
	}
}
