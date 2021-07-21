package com.kieran.app.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kieran.app.model.Image;

public interface ImageRepo extends JpaRepository<Image,Long> {
	
	Optional<Image> findByName(String name);

}
