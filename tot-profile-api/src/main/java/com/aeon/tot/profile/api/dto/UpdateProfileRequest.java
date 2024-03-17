package com.aeon.tot.profile.api.dto;

import java.time.LocalDate;

import com.aeon.tot.profile.api.config.Messages;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateProfileRequest(
		
		@NotNull(message = Messages.NAME_NOT_NULL)
		@Size(min = 3, max = 50, message = Messages.NAME_SIZE)
		@Pattern(regexp = "[a-zA-Z\\sáàâãéèêíìóòôõúùûç]+", message = Messages.NAME_PATTERN)
		String name, 
		
		@NotNull(message = Messages.LASTNAME_NOT_NULL)
		@Size(min = 3, max = 50, message = Messages.LASTNAME_SIZE)
		@Pattern(regexp = "[a-zA-Z\\sáàâãéèêíìóòôõúùûç]+", message = Messages.LASTNAME_PATTERN)
		String lastName,
		
		LocalDate birthDate, 
		
		@Size(max = 1000, message = Messages.BIOGRAPHY_SIZE)
		String biography) {
	
}
