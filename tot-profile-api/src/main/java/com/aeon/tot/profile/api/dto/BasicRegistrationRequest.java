package com.aeon.tot.profile.api.dto;

import com.aeon.tot.profile.api.config.Messages;

import jakarta.validation.constraints.NotNull;

public record BasicRegistrationRequest(
		@NotNull(message = Messages.USER_ID_NOT_NULL)
		Long userId
		) {
}
