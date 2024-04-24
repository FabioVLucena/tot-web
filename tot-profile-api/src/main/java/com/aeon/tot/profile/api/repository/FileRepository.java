package com.aeon.tot.profile.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aeon.tot.profile.api.entity.File;

@Repository
public interface FileRepository extends JpaRepository<File, String> {

}
