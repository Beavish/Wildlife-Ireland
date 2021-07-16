package com.kieran.app.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kieran.app.model.Post;
import com.kieran.app.model.User;
import com.kieran.app.repo.PostRepo;
import com.kieran.app.repo.UserRepo;
@Controller
@RequestMapping(path = "/api/")
public class PostController {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepo userRepo;


	@GetMapping(path = "/post/all")
	public @ResponseBody Iterable<Post> getAllPosts() {
		// This returns a JSON or XML with the users
		return postRepo.findAll();
	}

	@GetMapping("/post/{id}")
	public ResponseEntity<?> getPost(@PathVariable Long id) {
		Optional<Post> post = postRepo.findById(id);
		return post.map(response -> ResponseEntity.ok().body(response))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

	}
	@PostMapping("/post/new")
	public ResponseEntity<Post> createPost(@Validated @RequestBody Post post){
		Optional<User> loginUser = userRepo.findByUsername(post.getUsername());
		Long user_id =loginUser.get().getUserId();
		
		Post newPost = new Post();
		newPost.setTitle(post.getTitle());
		newPost.setContent(post.getContent());
		newPost.setCreate_date(post.getCreate_date());
		newPost.setUsername(post.getUsername());
		newPost.setUserId(user_id);
		newPost.setEducational(post.isEducational());
		Post result= postRepo.save(newPost);
		
		return ResponseEntity.ok().body(result);
	}
	
	
	
	@PutMapping("/post/{id}")
	ResponseEntity<Post> updateCategory(@Validated @RequestBody Post post, @PathVariable Long id){
		Post result= postRepo.save(post);
		return ResponseEntity.ok().body(result);
	}
	
	
	

	@DeleteMapping("/post/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
		postRepo.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/post/by-user/{username}")
	public @ResponseBody Iterable<Post> postByUsername(@PathVariable String username) {
			
		return postRepo.findByUsername(username);
		 
	}
	
	@GetMapping("/post/educational")
	public @ResponseBody Iterable<Post> postEducational() {
			
		return postRepo.findByEducation();
				}

}