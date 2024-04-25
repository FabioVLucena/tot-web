package com.aeon.tot.post.api.dto;

import java.time.LocalDate;

public record GetProfileByIdResponse(Long id, Long userId, String name,
		String lastName, LocalDate birthDate, String biography,
		String photoUrl) {

}
