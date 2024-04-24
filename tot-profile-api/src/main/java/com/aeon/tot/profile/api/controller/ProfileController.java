package com.aeon.tot.profile.api.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aeon.tot.profile.api.bo.ProfileBO;
import com.aeon.tot.profile.api.dto.GetProfileByIdResponse;
import com.aeon.tot.profile.api.dto.UpdateProfileRequest;
import com.aeon.tot.profile.api.entity.File;
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
	
	@PutMapping("/{profileId}")
	public GetProfileByIdResponse updateProfile(@PathVariable Long profileId, @RequestBody @Valid UpdateProfileRequest req) throws WarningException {
		Profile profile = profileBO.update(profileId, req);
		
		return GetProfileByIdResponse.convert(profile);
	}

	@GetMapping("/{profileId}/photo")
	public ResponseEntity<Resource> getProfilePhoto(@PathVariable Long profileId) throws Exception {
		File photo = profileBO.getPhotoByProfileId(profileId);
		
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(photo.getType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + photo.getName() + "\"")
				.body(new ByteArrayResource(photo.getData()));
	}
	
	@PutMapping("/{profileId}/photo")
	public ResponseEntity<?> updateProfilePhoto(@PathVariable Long profileId, @RequestParam MultipartFile file) throws Exception {
		profileBO.updateProfilePhoto(profileId, file);

		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{profileId}/photo")
	public ResponseEntity<?> deleteProfilePhoto(@PathVariable Long profileId) throws WarningException {
		profileBO.deleteProfilePhoto(profileId);
		
		return ResponseEntity.noContent().build();
	}
}
