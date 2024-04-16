package com.aeon.tot.profile.api.bo;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.aeon.tot.profile.api.dto.BasicRegistrationRequest;
import com.aeon.tot.profile.api.dto.UpdateProfileRequest;
import com.aeon.tot.profile.api.entity.Profile;
import com.aeon.tot.profile.api.exception.WarningException;
import com.aeon.tot.profile.api.repository.ProfileRepository;
import com.aeon.tot.profile.api.service.ProfileService;

@Component
public class ProfileBO {

	private ProfileService profileService;
	private ProfileRepository profileRepository;

	public ProfileBO(ProfileService profileService, ProfileRepository profileRepository) {
		this.profileService = profileService;
		this.profileRepository = profileRepository;
	}
	
	public Profile basicRegistration(BasicRegistrationRequest req) throws WarningException {
		Long userId = req.userId();
		
		validateUserIdInUse(userId);
		
		Profile profile = new Profile();
		profile.setUserId(userId);
		
		return profileRepository.save(profile);
	}
	
	public Profile update(Long profileId, UpdateProfileRequest req) throws WarningException {
		String name = req.name();
		String lastName = req.lastName();
		LocalDate birthDate = req.birthDate();
		String biography = req.biography();
		
		Profile profile = profileService.getProfileById(profileId);
		
		if (profile == null)
			throw new WarningException("Profile not found!");
		
		profile.setName(name);
		profile.setLastName(lastName);
		profile.setBirthDate(birthDate);
		profile.setBiography(biography);
		
		return profileRepository.save(profile);
	}

	private void validateUserIdInUse(Long userId) throws WarningException {
		Profile profile = this.profileService.getProfileByUserId(userId);
		
		if (profile != null)
			throw new WarningException("User already has a profile!");		
	}
}
