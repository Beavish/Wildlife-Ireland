package com.kieran.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kieran.app.model.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepo extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    void deleteByToken(String token);
}