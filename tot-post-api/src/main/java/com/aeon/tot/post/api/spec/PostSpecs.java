package com.aeon.tot.post.api.spec;

import org.springframework.data.jpa.domain.Specification;

import com.aeon.tot.post.api.entity.Post;

public class PostSpecs {

	public static Specification<Post> profileIdEquals(Long profileId) {
		return (root, query, cb) -> cb.equal(root.get("profileId"), profileId);
	}

	public static Specification<Post> titleLike(String title) {
		return (root, query, cb) -> cb.like(root.get("title"), "%" + title + "%");
	}
	
	public static Specification<Post> contentLike(String content) {
		return (root, query, cb) -> cb.like(root.get("content"), "%" + content + "%");
	}

	public static Specification<Post> deletedAtIsNull() {
		return (root, query, cb) -> cb.isNull(root.get("deletedAt"));
	}
}
