package com.kieran.app.controller;


import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson. *;

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
	
	Gson gson = new Gson();

	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/login")
	public User authenticateUser( @RequestBody() Map<?, ?> creds ) {
		
		Map <?,?> data = (Map<?, ?>) creds.get("data");

		
		User loginUser = userRepo.findByEmail(data.get("username").toString());
		String loginPword = data.get("password").toString();
		User result = userService.loginUser(loginUser,loginPword);
		System.out.print(result);
		return result;
			
			

	}
	
	
	@PostMapping("/sign-up")
	String signUp(User user) {
		userService.signUpUser(user);
		return "Success";
	}

	@GetMapping("/sign-up/confirm")
	String confirmMail(@RequestParam("token") String token) {
		Optional<ConfirmationToken> optionalConfirmationToken = confirmationTokenService.findConfirmationTokenByToken(token);
		optionalConfirmationToken.ifPresent(userService::confirmUser);
		return "Success";
	}

}