package com.aeon.tot.profile.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aeon.tot.profile.api.bo.ProfileBO;
import com.aeon.tot.profile.api.dto.CreateSimpleProfileRequest;
import com.aeon.tot.profile.api.dto.CreateSimpleProfileResponse;
import com.aeon.tot.profile.api.dto.GetProfileByIdResponse;
import com.aeon.tot.profile.api.dto.UpdateProfileRequest;
import com.aeon.tot.profile.api.entity.Profile;
import com.aeon.tot.profile.api.exception.WarningException;
import com.aeon.tot.profile.api.service.ProfileService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/profiles")
public class ProfileController {

	private ProfileBO profileBO;
	private ProfileService profileService;
	
	public ProfileController(ProfileBO profileBO, ProfileService profileService) {
		this.profileBO = profileBO;
		this.profileService = profileService;
	}

	@GetMapping("/{profileId}")
	public GetProfileByIdResponse getProfileById(@PathVariable Long profileId) {
		Profile profile = profileService.getProfileById(profileId);
		
		if (profile == null) profile = new Profile();
		
		return GetProfileByIdResponse.convert(profile);
	}

	@GetMapping("/users/{userId}")
	public GetProfileByIdResponse getProfileByUserId(@PathVariable Long userId) {
		Profile profile = profileService.getProfileByUserId(userId);
		
		if (profile == null) profile = new Profile();
		
		return GetProfileByIdResponse.convert(profile);
	}
	
	@PostMapping
	public CreateSimpleProfileResponse createSimpleProfile(@RequestBody @Valid CreateSimpleProfileRequest req) throws WarningException {
		Profile profile = profileBO.createSimple(req);
		
		return CreateSimpleProfileResponse.convert(profile);
	}

	@PutMapping("/{profileId}")
	public GetProfileByIdResponse updateProfile(@PathVariable Long profileId, @RequestBody @Valid UpdateProfileRequest req) throws WarningException {
		Profile profile = profileBO.update(profileId, req);
		
		return GetProfileByIdResponse.convert(profile);
	}
}
