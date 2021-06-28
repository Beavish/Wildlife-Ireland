package com.kieran.app.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kieran.app.dto.PostRequest;
import com.kieran.app.model.Post;
import com.kieran.app.model.User;

public interface PostRepo extends JpaRepository<Post,Long>{

	//Optional<Post> findByUser(String userID);

	Iterable<Post> findByUsername(String username);


	

	

}

