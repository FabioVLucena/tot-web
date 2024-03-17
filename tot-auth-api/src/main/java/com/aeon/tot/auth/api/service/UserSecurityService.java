package com.aeon.tot.auth.api.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.aeon.tot.auth.api.repository.UserRepository;

@Service
public class UserSecurityService {
    
	private UserRepository userRepository;
    
	public UserSecurityService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
    public UserDetailsService userDetailsService() {
        
    	return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userRepository.findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
            }
        };
    }
}
