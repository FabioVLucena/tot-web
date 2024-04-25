package com.aeon.tot.post.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aeon.tot.post.api.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
