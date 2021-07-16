package com.kieran.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kieran.app.model.Post;
@Repository 
public interface PostRepo extends JpaRepository<Post,Long>{

	//Optional<Post> findByUser(String userID);

	Iterable<Post> findByUsername(String username);

	@Query(value = "SELECT * FROM Post WHERE post.educational = true",nativeQuery = true)
	Iterable<Post> findByEducation();

    	


	

	

}

