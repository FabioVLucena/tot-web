package com.aeon.tot.post.api.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.aeon.tot.post.api.client.ProfileClient;
import com.aeon.tot.post.api.dto.GetProfileByIdResponse;
import com.aeon.tot.post.api.dto.PostRequest;
import com.aeon.tot.post.api.entity.Post;
import com.aeon.tot.post.api.exception.WarningException;
import com.aeon.tot.post.api.repository.PostRepository;
import com.aeon.tot.post.api.spec.PostSpecs;

@Service
public class PostService {

	private PostRepository postRepository;
	
	private ProfileClient profileClient;
	
	public PostService(PostRepository postRepository, ProfileClient profileClient) {
		this.postRepository = postRepository;
		this.profileClient = profileClient;
	}
	
	public List<Post> selectAll(String title,
			String content,
			Long profileId) {
		
		Specification<Post> specs = 
				Specification.where((root, query, cb) -> cb.conjunction());
		
		specs = specs.and(PostSpecs.deletedAtIsNull());
		
		if (title != null)
			specs = specs.and(PostSpecs.titleLike(title));

		if (content != null)
			specs = specs.and(PostSpecs.contentLike(content));

		if (profileId != null)
			specs = specs.and(PostSpecs.profileIdEquals(profileId));
		
		return this.postRepository.findAll(specs);
	}

	public Post getPostById(Long id) throws WarningException {
		return this.postRepository.findById(id)
				.orElseThrow(() -> new WarningException("Post not found!"));
	}
	
	public Post createPost(PostRequest req) throws WarningException {
		Long profileId = req.profileId();

		GetProfileByIdResponse profile = this.profileClient.getProfileById(profileId);
		if (profile.id() == null)
			throw new WarningException("Profile not found!");
		
		String title = req.title();
		String content = req.content();
		
		Post post = new Post();
		post.setProfileId(profileId);
		post.setTitle(title);
		post.setContent(content);
		
		return this.postRepository.save(post);
	}

	public Post updatePost(Long id, PostRequest req) throws WarningException {
		String title = req.title();
		String content = req.content();
		
		Post post = getPostById(id);
		post.setTitle(title);
		post.setContent(content);
		
		return this.postRepository.save(post);
	}
	
	public void deletePostById(Long id) throws WarningException {
		Post post = getPostById(id);
		post.setDeletedAt(LocalDate.now());
		
		this.postRepository.save(post);
	}
}
