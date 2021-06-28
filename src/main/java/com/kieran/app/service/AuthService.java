package com.kieran.app.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.kieran.app.dto.AuthenticationResponse;
import com.kieran.app.dto.LoginRequest;
import com.kieran.app.dto.RefreshTokenRequest;
import com.kieran.app.dto.RegisterRequest;
import com.kieran.app.exceptions.WildlifeIrelandException;
import com.kieran.app.model.NotificationEmail;
import com.kieran.app.model.User;
import com.kieran.app.model.VerificationToken;
import com.kieran.app.repo.UserRepo;
import com.kieran.app.repo.VerificationTokenRepo;
import com.kieran.app.security.JwtProvider;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {
	/*
	 * Spring says we should user constructor injection rather than field inject as
	 * such, We may wish to reconsider using the autowired annotation
	 * 
	 * previously we have autowired password encoder and user repo, but instead we
	 * have declared them as final and used the all args constructor to initalise
	 * them, this in now constructor injection rather than field
	 * 
	 * 
	 */

	private final PasswordEncoder passwordEncoder;
	private final VerificationTokenRepo verificationTokenRepo;
	private final UserRepo userRepo;
	private final MailService mailService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;
    

	@Transactional
	public void signup(@RequestBody RegisterRequest registerRequest) {
		User user = new User();
		user.setUsername(registerRequest.getUsername());
		user.setEmail(registerRequest.getEmail());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		user.setCreated(Instant.now());
		user.setEnabled(false);
		userRepo.save(user);

		generateVerificationToken(user);
		String token = generateVerificationToken(user);

		mailService.sendMail(new NotificationEmail("Please Activate your Account", user.getEmail(),
				"Thank you for signing up to WildLife Ireland, "
						+ "please click on the below url to activate your account : "
						+ "http://localhost:8080/api/auth/accountVerification/" + token));

	}

	private String generateVerificationToken(User user) {
		String token = UUID.randomUUID().toString();
		VerificationToken verificationToken = new VerificationToken();
		verificationToken.setToken(token);
		verificationToken.setUser(user);

		verificationTokenRepo.save(verificationToken);
		return token;
	}

	 public void verifyAccount(String token) {
	        Optional<VerificationToken> verificationToken = verificationTokenRepo.findByToken(token);
	        fetchUserAndEnable(verificationToken.orElseThrow(() -> new WildlifeIrelandException("Invalid Token")));
	    }

	 private void fetchUserAndEnable(VerificationToken verificationToken) {
	        String username = verificationToken.getUser().getUsername();
	        User user = userRepo.findByUsername(username).orElseThrow(() -> new WildlifeIrelandException("User not found with name - " + username));
	        user.setEnabled(true);
	        userRepo.save(user);
	    }

	 public AuthenticationResponse login(LoginRequest loginRequest) {
		 	
		Optional<User> loginUser = userRepo.findByUsername(loginRequest.getUsername());
		Long user_id =loginUser.get().getUserId();
		 	
	        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
	                loginRequest.getPassword()));
	        SecurityContextHolder.getContext().setAuthentication(authenticate);
	        String token = jwtProvider.generateToken(authenticate);
	        return AuthenticationResponse.builder()
	                .authenticationToken(token)
	                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
	                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
	                .username(loginRequest.getUsername())
	                .user_id(user_id)
	                .build();
	    }

	public AuthenticationResponse refreshToken(@Valid RefreshTokenRequest refreshTokenRequest) {
		 refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
		 jwtProvider.generateTokenWithUserName(refreshTokenRequest.getUsername());
		 
		return null;
	}

	public Object getCurrentUser() {
		// TODO Auto-generated method stub
		return null;
	}
}
