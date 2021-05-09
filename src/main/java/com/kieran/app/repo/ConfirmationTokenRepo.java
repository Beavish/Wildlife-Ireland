package com.kieran.app.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kieran.app.model.ConfirmationToken;

@Repository
public interface ConfirmationTokenRepo extends JpaRepository<ConfirmationToken, Long> {

	Optional<ConfirmationToken> findConfirmationTokenByConfirmationToken(String token);

}