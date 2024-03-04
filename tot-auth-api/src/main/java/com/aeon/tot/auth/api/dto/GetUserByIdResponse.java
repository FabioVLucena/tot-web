package com.aeon.tot.auth.api.dto;

import java.time.LocalDate;

import com.aeon.tot.auth.api.entity.User;

public record GetUserByIdResponse(Long id, String username, 
		LocalDate createdDate, LocalDate deletedDate) {

	public static GetUserByIdResponse convert(User user) {
		Long id = user.getId();
		String username = user.getUsername();
		LocalDate createdDate = user.getCreatedAt();
		LocalDate deletedDate = user.getDeletedAt();		
		
		GetUserByIdResponse res = new GetUserByIdResponse(id, username, 
				createdDate, deletedDate);
		
		return res;
	}
}
