package com.aeon.tot.auth.api.bo;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.aeon.tot.auth.api.dto.BasicRegistrationRequest;
import com.aeon.tot.auth.api.dto.SigninRequest;
import com.aeon.tot.auth.api.dto.SigninResponse;
import com.aeon.tot.auth.api.dto.SignupRequest;
import com.aeon.tot.auth.api.dto.SignupResponse;
import com.aeon.tot.auth.api.entity.User;
import com.aeon.tot.auth.api.exception.WarningException;
import com.aeon.tot.auth.api.facade.ProfileFacade;
import com.aeon.tot.auth.api.service.JwtService;
import com.aeon.tot.auth.api.service.UserService;

@Component
public class AuthBO {

	private UserBO userBO;
	private JwtService jwtService;
	private UserService userService;
    private PasswordEncoder passwordEncoder;
    private ProfileFacade profileFacade;
	
	public AuthBO(UserBO userBO, JwtService jwtService, 
			UserService userService, PasswordEncoder passwordEncoder,
			ProfileFacade profileFacade) {
	
		this.userBO = userBO;
		this.jwtService = jwtService;
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
		this.profileFacade = profileFacade;
	}
	
	public SigninResponse signin(SigninRequest req) throws WarningException {
		String username = req.username();
		String password = req.password();
		
		User user = this.userService.getUserByUsername(username);
		
		if (user == null) 
			throw new WarningException("Username or login invalid!");
		
		Boolean isMatch = this.passwordEncoder.matches(password, user.getPassword()); 
		
		if (isMatch == false) 
			throw new WarningException("Username or login invalid!");
		
		String jwt = this.jwtService.generateToken(user);
		
		return new SigninResponse(jwt);
	}

	public SignupResponse signup(SignupRequest req) throws Exception {
		String username = req.username();
		String password = req.password();
		String repassword = req.repassword();
		
		Boolean isMatch = password.equals(repassword); 
		
		if (isMatch == false) 
			throw new WarningException("Passwords not matching!");
		
		String hashPassword = this.passwordEncoder.encode(password);
		
		User user = userBO.create(username, hashPassword);
		
		profileFacade.basicRegistration(new BasicRegistrationRequest(user.getId()));
		
		String jwt = this.jwtService.generateToken(user);
		
		return new SignupResponse(jwt);
	}
}
