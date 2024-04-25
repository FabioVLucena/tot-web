package com.aeon.tot.post.api.dto;

import java.time.LocalDate;

import com.aeon.tot.post.api.entity.Post;

public record PostResponse(Long id, Long profileId, String title,
		String content, LocalDate createdAt) {
	
	public static PostResponse convert(Post post) {
		return new PostResponse(post.getId(),
				post.getProfileId(),
				post.getTitle(),
				post.getContent(),
				post.getCreatedAt());
	}
}
