package com.kieran.app.controller;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kieran.app.model.Image;
import com.kieran.app.repo.ImageRepo;
import com.sun.istack.NotNull;


@Controller
@RequestMapping(path = "/api/")
public class ImageController {
	
	@Autowired
	private ImageRepo imageRepo;
	
	@PostMapping("/image/new")
	public ResponseEntity<Image> uploadNewFile(@NotNull @RequestParam("file") MultipartFile file,
			@RequestParam("record_id") Long postId)
					throws IOException {
		// Now we need to dynamically pass post id to this function
	  Image image = new Image( null, file.getOriginalFilename(),file.getContentType(),file.getBytes(), postId, null);
      System.out.println(postId);


	  Image res = imageRepo.save(image);
	  URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
	  System.out.println(res);
	  
	  return ResponseEntity.ok(res);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Image> updateFile(@NotNull @RequestParam("file") MultipartFile file,
			@RequestParam("record_id") Long postId,@RequestParam("id") Long id)
					throws IOException {
		System.out.print(postId);
		System.out.print(id);
		// Now we need to dynamically pass post id to this function
		
		  Image image = new Image( id,
		  file.getOriginalFilename(),file.getContentType(),file.getBytes(), postId,
		  null); System.out.println(postId);
		  
		  
		  Image res = imageRepo.save(image); URI location =
		  ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		  System.out.println(res);
		  
		   return ResponseEntity.ok(res);
		 
	  
	 	}
	
	@GetMapping("/{id}")
	public	ResponseEntity<?> getImage(@PathVariable Long id){
		Optional<Image> image = imageRepo.findById(id);
		 return image.map(response -> ResponseEntity.ok().body(response))
				 .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
		}
	
	@GetMapping("/all")
	  public @ResponseBody Iterable<Image> getAllImages() {
	    // This returns a JSON or XML with the users
	    return imageRepo.findAll();
	  }

}
