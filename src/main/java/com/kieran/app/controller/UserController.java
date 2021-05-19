package com.kieran.app.controller;


import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kieran.app.model.ConfirmationToken;
import com.kieran.app.model.User;
import com.kieran.app.repo.UserRepo;
import com.kieran.app.service.ConfirmationTokenService;
import com.kieran.app.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class UserController {

	private final UserService userService;

	private final ConfirmationTokenService confirmationTokenService;
	
	private final UserRepo userRepo;

	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser( @RequestBody() Object creds ) {
		System.out.print(creds.toString());
		return    null ;
	}
	/*public ResponseEntity<?> authenticateUser( @RequestParam("username") String username,  @RequestParam("password") String password   ) {
		System.out.print(username);
		return    null ;
	}*/
	
	@PostMapping("/sign-up")
	String signUp(User user) {

		userService.signUpUser(user);
		
		return "Success";
		//"redirect:/sign-in";
	}

	@GetMapping("/sign-up/confirm")
	String confirmMail(@RequestParam("token") String token) {

		Optional<ConfirmationToken> optionalConfirmationToken = confirmationTokenService.findConfirmationTokenByToken(token);

		optionalConfirmationToken.ifPresent(userService::confirmUser);

		//return "redirect:/sign-in";
		return "Success";
	}

}