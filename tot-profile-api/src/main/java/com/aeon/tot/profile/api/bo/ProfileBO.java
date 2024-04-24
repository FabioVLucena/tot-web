package com.aeon.tot.profile.api.bo;

import java.time.LocalDate;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.aeon.tot.profile.api.dto.BasicRegistrationRequest;
import com.aeon.tot.profile.api.dto.UpdateProfileRequest;
import com.aeon.tot.profile.api.entity.File;
import com.aeon.tot.profile.api.entity.Profile;
import com.aeon.tot.profile.api.exception.WarningException;
import com.aeon.tot.profile.api.repository.ProfileRepository;
import com.aeon.tot.profile.api.service.FileService;
import com.aeon.tot.profile.api.service.ProfileService;

@Component
public class ProfileBO {

	private ProfileService profileService;
	private ProfileRepository profileRepository;

	private FileService fileService;
	
	public ProfileBO(ProfileService profileService,
			ProfileRepository profileRepository,
			FileService fileService) {
		
		this.profileService = profileService;
		this.profileRepository = profileRepository;
		this.fileService = fileService;
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
	
	public void updateProfilePhoto(Long profileId, MultipartFile multipartFile) throws Exception {
		Profile profile = profileService.getProfileById(profileId);
		
		if (profile == null)
			throw new WarningException("Profile not found!");
		
		File oldFile = profile.getPhoto();
			
		File file = this.fileService.storeFile(multipartFile);
		
		profile.setPhoto(file);
		
		this.profileRepository.save(profile);
		
		if (oldFile != null)
			this.fileService.deleteFile(oldFile);
	}

	public void deleteProfilePhoto(Long profileId) throws WarningException {
		Profile profile = profileService.getProfileById(profileId);
		
		if (profile == null)
			throw new WarningException("Profile not found!");
		
		File file = profile.getPhoto();

		profile.setPhoto(null);
		this.profileRepository.save(profile);

		if (file != null)
			this.fileService.deleteFile(file);
	}
	
	public File getPhotoByProfileId(Long profileId) throws WarningException {
		Profile profile = profileService.getProfileById(profileId);
		
		if (profile == null)
			throw new WarningException("Profile not found!");
		
		File photo = profile.getPhoto();
		if (photo == null)
			throw new WarningException("Photo not found!");
		
		return photo;
	}

	private void validateUserIdInUse(Long userId) throws WarningException {
		Profile profile = this.profileService.getProfileByUserId(userId);
		
		if (profile != null)
			throw new WarningException("User already has a profile!");		
	}
}
