package com.kieran.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kieran.app.model.Crime;

public interface CrimeRepo extends JpaRepository<Crime,Long>{

}

