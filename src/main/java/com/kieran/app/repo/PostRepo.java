package com.kieran.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kieran.app.model.Post;

public interface PostRepo extends JpaRepository<Post,Long>{

	//Optional<Post> findByUser(String userID);

	Iterable<Post> findByUsername(String username);
	


	

	

}

