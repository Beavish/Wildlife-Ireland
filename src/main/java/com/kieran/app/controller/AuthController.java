package com.kieran.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kieran.app.dto.AuthenticationResponse;
import com.kieran.app.dto.LoginRequest;
import com.kieran.app.dto.RefreshTokenRequest;
import com.kieran.app.dto.RegisterRequest;
import com.kieran.app.service.AuthService;
import com.kieran.app.service.RefreshTokenService;

import static org.springframework.http.HttpStatus.OK;

import javax.validation.Valid;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

	private final AuthService authService;
    private final RefreshTokenService refreshTokenService;


	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
		authService.signup(registerRequest);
		return new ResponseEntity<>("User Registration Successful", OK);
	}

	@GetMapping("accountVerification/{token}")
	public ResponseEntity<String> verifyAccount(@PathVariable String token) {
		authService.verifyAccount(token);
		return new ResponseEntity<>("Account Activated Successfully", OK);
	}
	 @PostMapping("/login")
	    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
	        return authService.login(loginRequest);
	    }
	 @PostMapping("refresh/token")
	 public AuthenticationResponse refreshTokens(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest ) {
		return authService.refreshToken(refreshTokenRequest);
		 
	 }
	  @PostMapping("/logout")
	    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
	        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
	        return ResponseEntity.status(OK).body("Refresh Token Deleted Successfully!!");
	    }
}
