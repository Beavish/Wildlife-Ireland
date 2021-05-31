package com.kieran.app.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kieran.app.model.User;

public interface UserRepo extends JpaRepository<User,Long>{
	
	User findByEmail(String email);

}
