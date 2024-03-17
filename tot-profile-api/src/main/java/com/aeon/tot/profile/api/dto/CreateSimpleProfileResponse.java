package com.aeon.tot.profile.api.dto;

import com.aeon.tot.profile.api.entity.Profile;

public record CreateSimpleProfileResponse(Long id, Long userId) {

	public static CreateSimpleProfileResponse convert(Profile profile) {
		Long id = profile.getId();
		Long userId = profile.getUserId();
		
		return new CreateSimpleProfileResponse(id, userId);
	}
}
