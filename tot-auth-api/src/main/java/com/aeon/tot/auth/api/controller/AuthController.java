package com.aeon.tot.auth.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aeon.tot.auth.api.bo.AuthBO;
import com.aeon.tot.auth.api.dto.SigninRequest;
import com.aeon.tot.auth.api.dto.SigninResponse;
import com.aeon.tot.auth.api.dto.SignupRequest;
import com.aeon.tot.auth.api.dto.SignupResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	private AuthBO authBO;
	
	public AuthController(AuthBO authBO) {
		this.authBO = authBO;
	}
	
	@PostMapping("/signin")
	public ResponseEntity<SigninResponse> signin(@RequestBody @Valid SigninRequest req) throws Exception {
		
		SigninResponse res = authBO.signin(req);
		
		return ResponseEntity.status(HttpStatus.OK).body(res);
	}

	@PostMapping("/signup")
	public ResponseEntity<SignupResponse> signup(@RequestBody @Valid SignupRequest req) throws Exception {

		SignupResponse res = authBO.signup(req);
		
		return ResponseEntity.status(HttpStatus.OK).body(res);
	}
}
