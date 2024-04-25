package com.aeon.tot.post.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.aeon.tot.post.api.dto.GetProfileByIdResponse;

@Component
@FeignClient(name = "tot-profile-api", path = "/api/v1/profiles")
public interface ProfileClient {

	@GetMapping("/{profileId}")
	GetProfileByIdResponse getProfileById(@PathVariable Long profileId);
	
}
