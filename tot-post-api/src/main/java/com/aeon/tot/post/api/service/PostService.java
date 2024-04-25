package com.aeon.tot.post.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aeon.tot.post.api.client.ProfileClient;
import com.aeon.tot.post.api.dto.GetProfileByIdResponse;
import com.aeon.tot.post.api.dto.PostRequest;
import com.aeon.tot.post.api.entity.Post;
import com.aeon.tot.post.api.exception.WarningException;
import com.aeon.tot.post.api.repository.PostRepository;

@Service
public class PostService {

	private PostRepository postRepository;
	
	private ProfileClient profileClient;
	
	public PostService(PostRepository postRepository, ProfileClient profileClient) {
		this.postRepository = postRepository;
		this.profileClient = profileClient;
	}
	
	public List<Post> selectAll() {
		return this.postRepository.findAll();
	}

	public Post getPostById(Long id) {
		return this.postRepository.findById(id).orElse(null);
	}
	
	public Post createPost(PostRequest req) throws WarningException {
		Long profileId = req.profileId();

		GetProfileByIdResponse profileRes = this.profileClient.getProfileById(profileId);
		if (profileRes.id() == null)
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
		if (post == null)
			throw new WarningException("Post not found!");
		
		post.setTitle(title);
		post.setContent(content);
		
		return this.postRepository.save(post);
	}
	
	public void deletePost(Long id) {
		this.postRepository.deleteById(id);
	}
}
