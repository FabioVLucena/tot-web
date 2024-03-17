package com.aeon.tot.profile.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aeon.tot.profile.api.entity.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

	Optional<Profile> findById(Long id);
	
	Optional<Profile> findByUserId(Long userId);
	
}
