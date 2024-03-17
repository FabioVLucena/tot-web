package com.aeon.tot.profile.api.dto;

import java.time.LocalDate;

import com.aeon.tot.profile.api.entity.Profile;

public record GetProfileByIdResponse(Long id, Long userId, String name,
		String lastName, LocalDate birthDate, String biography) {

	public static GetProfileByIdResponse convert(Profile profile) {
		Long id = profile.getId();
		Long userId = profile.getUserId();
		String name = profile.getName();
		String lastName = profile.getLastName();
		LocalDate birthDate = profile.getBirthDate();
		String biography = profile.getBiography();
		
		return new GetProfileByIdResponse(id, userId, name,
				lastName, birthDate, biography);
	}
}
