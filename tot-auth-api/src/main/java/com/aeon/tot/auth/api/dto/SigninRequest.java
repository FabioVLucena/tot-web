package com.aeon.tot.auth.api.dto;

import com.aeon.tot.auth.api.config.Messages;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record SigninRequest(
		@NotNull(message = Messages.USERNAME_NOT_NULL)
		@Size(min = 5, max = 20, message = Messages.USERNAME_SIZE)
		@Pattern(regexp = "^[a-zA-Z0-9]+$", message = Messages.USERNAME_PATTERN)
		String username,
		
		@NotNull(message = Messages.PASSWORD_NOT_NULL)
		@Size(min = 8, message = Messages.PASSWORD_MIN_SIZE)
		@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\\$%^&*()-+=]).*$", message = Messages.PASSWORD_PATTERN)
		String password) {
}
