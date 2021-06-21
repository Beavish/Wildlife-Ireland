package com.kieran.app.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kieran.app.model.Post;
import com.kieran.app.repo.PostRepo;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path = "/api/post")
public class PostController {

	@Autowired
	private PostRepo postRepo;


	@GetMapping(path = "/all")
	public @ResponseBody Iterable<Post> getAllPosts() {
		// This returns a JSON or XML with the users
		return postRepo.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getPost(@PathVariable Long id) {
		Optional<Post> post = postRepo.findById(id);
		return post.map(response -> ResponseEntity.ok().body(response))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

	}
	
	@PostMapping("/new")
	ResponseEntity<Post> createPost(@Validated @RequestBody Post post){
		Post result= postRepo.save(post);
		return ResponseEntity.ok().body(result);
	}
	
	
	
	@PutMapping("/update/{id}")
	ResponseEntity<Post> updatePost(@Validated @RequestBody Post post, @PathVariable Long id){
		Post result= postRepo.save(post);
		return ResponseEntity.ok().body(result);
	}
	

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
		postRepo.deleteById(id);
		return ResponseEntity.ok().build();
	}

}