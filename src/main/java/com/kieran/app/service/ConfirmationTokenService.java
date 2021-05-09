package com.kieran.app.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kieran.app.model.ConfirmationToken;
import com.kieran.app.repo.ConfirmationTokenRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {


	private final ConfirmationTokenRepo confirmationTokenRepository;

	void saveConfirmationToken(ConfirmationToken confirmationToken) {

		confirmationTokenRepository.save(confirmationToken);
	}

	void deleteConfirmationToken(Long id) {

		confirmationTokenRepository.deleteById(id);
	}


	public Optional<ConfirmationToken> findConfirmationTokenByToken(String token) {

		return confirmationTokenRepository.findConfirmationTokenByConfirmationToken(token);
	}

}
