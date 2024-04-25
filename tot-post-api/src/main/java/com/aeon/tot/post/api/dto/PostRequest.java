package com.aeon.tot.post.api.dto;


import com.aeon.tot.post.api.config.Messages;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PostRequest(
		@NotNull(message = Messages.PROFILE_ID_NOT_NULL)
		Long profileId,
		
		@NotNull(message = Messages.TITLE_NOT_NULL)
		@Size(min = 3, max = 50, message = Messages.TITLE_SIZE)
		String title, 
		
		@NotNull(message = Messages.CONTENT_NOT_NULL)
		@Size(min = 3, max = 200, message = Messages.CONTENT_SIZE)
		String content) {
	
}
