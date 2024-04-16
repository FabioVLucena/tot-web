package com.aeon.tot.profile.api.dto;

import com.aeon.tot.profile.api.entity.Profile;

public record BasicRegistrationResponse(Long id, Long userId) {

	public static BasicRegistrationResponse convert(Profile profile) {
		Long id = profile.getId();
		Long userId = profile.getUserId();
		
		return new BasicRegistrationResponse(id, userId);
	}
}
