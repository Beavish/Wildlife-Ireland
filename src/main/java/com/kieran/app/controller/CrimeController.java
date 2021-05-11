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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kieran.app.model.Crime;
import com.kieran.app.repo.CrimeRepo;
@Controller
@RequestMapping(path = "/api/v1")
public class CrimeController {

	@Autowired
	private CrimeRepo crimeRepo;


	@GetMapping(path = "/crime/all")
	public @ResponseBody Iterable<Crime> getAllCrimes() {
		return crimeRepo.findAll();
	}

	@GetMapping("/crime/{id}")
	public ResponseEntity<?> getCrime(@PathVariable Long id) {
		Optional<Crime> crime = crimeRepo.findById(id);
		return crime.map(response -> ResponseEntity.ok().body(response))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

	}
	
	@PutMapping("/crime/{id}")
	ResponseEntity<Crime> updateCategory(@Validated @RequestBody Crime crime, @PathVariable Long id){
		Crime result= crimeRepo.save(crime);
		return ResponseEntity.ok().body(result);
	}
	

	@DeleteMapping("/crime/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
		crimeRepo.deleteById(id);
		return ResponseEntity.ok().build();
	}

}