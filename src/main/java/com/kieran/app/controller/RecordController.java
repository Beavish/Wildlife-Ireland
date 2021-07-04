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

import com.kieran.app.model.Record;
import com.kieran.app.repo.RecordRepo;
@Controller
@RequestMapping(path = "/api/")
public class RecordController {

	@Autowired
	private RecordRepo recRepo;


	@GetMapping(path = "/record/all")
	public @ResponseBody Iterable<Record> getAllRecords() {
		return recRepo.findAll();
	}

	@GetMapping("/record/{id}")
	public ResponseEntity<?> getRecord(@PathVariable Long id) {
		Optional<Record> record = recRepo.findById(id);
		return record.map(response -> ResponseEntity.ok().body(response))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

	}
	
	@PostMapping("/record/new")
	ResponseEntity<Record> createrecord(@Validated @RequestBody Record record){
		Record result= recRepo.save(record);
		return ResponseEntity.ok().body(result);
	}
	
	
	@PutMapping("/record/{id}")
	ResponseEntity<Record> updateRecord(@Validated @RequestBody Record record, @PathVariable Long id){
		Record result= recRepo.save(record);
		return ResponseEntity.ok().body(result);
	}
	

	@DeleteMapping("/record/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
		recRepo.deleteById(id);
		return ResponseEntity.ok().build();
	}

}