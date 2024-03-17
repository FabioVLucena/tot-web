package com.aeon.tot.profile.api.service;

import org.springframework.stereotype.Service;

import com.aeon.tot.profile.api.entity.Profile;
import com.aeon.tot.profile.api.repository.ProfileRepository;

@Service
public class ProfileService {

	private ProfileRepository profileRepository;
	
	public ProfileService(ProfileRepository profileRepository) {
		this.profileRepository = profileRepository;
	}
	
	public Profile getProfileById(Long id) {
		return this.profileRepository.findById(id).orElse(null);
	}
	
	public Profile getProfileByUserId(Long userId) {
		return this.profileRepository.findByUserId(userId).orElse(null);
	}
}
