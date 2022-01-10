package com.kieran.app.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kieran.app.model.Image;
import com.kieran.app.model.PostImage;

public interface PostImageRepo extends JpaRepository<Image,Long> {
	
	Optional<Image> findByName(String name);

	PostImage save(PostImage image);

}
