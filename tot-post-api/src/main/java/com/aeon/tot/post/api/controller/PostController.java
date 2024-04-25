package com.aeon.tot.post.api.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.aeon.tot.post.api.dto.PostRequest;
import com.aeon.tot.post.api.dto.PostResponse;
import com.aeon.tot.post.api.entity.Post;
import com.aeon.tot.post.api.exception.WarningException;
import com.aeon.tot.post.api.service.PostService;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

	private PostService postService;
	
	public PostController(PostService postService) {
		this.postService = postService;
	}

	@GetMapping
	public ResponseEntity<List<PostResponse>> selectAll(@RequestParam(required = false) String title,
			@RequestParam(required = false) String content,
			@RequestParam(required = false) Long profileId) {
		
		List<Post> postList = this.postService.selectAll(title,
				content,
				profileId);
		
		List<PostResponse> res = postList.stream()
				.map(PostResponse::convert)
				.toList();
		
		return ResponseEntity.ok(res);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PostResponse> getPostById(@PathVariable Long id) throws WarningException {
		Post post = this.postService.getPostById(id);
		
		PostResponse res = PostResponse.convert(post); 
		
		return ResponseEntity.ok(res);
	}
	
	@PostMapping
	public ResponseEntity<PostResponse> createPost(@RequestBody PostRequest req) throws WarningException {
		Post post = this.postService.createPost(req);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/api/v1/posts/{id}")
				.buildAndExpand(post.getId())
				.toUri();

		PostResponse res = PostResponse.convert(post); 
		
        return ResponseEntity.created(uri).body(res);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PostResponse> updatePost(@PathVariable Long id, @RequestBody PostRequest req) throws WarningException {
		Post post = this.postService.updatePost(id, req);
		
		PostResponse res = PostResponse.convert(post); 
		
        return ResponseEntity.ok().body(res);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePost(@PathVariable Long id) throws WarningException {
		this.postService.deletePostById(id);
		
		return ResponseEntity.noContent().build();
	}
}
