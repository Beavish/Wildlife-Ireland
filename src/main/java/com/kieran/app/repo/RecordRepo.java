package com.kieran.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kieran.app.model.Record;

public interface RecordRepo extends JpaRepository<Record,Long>{

}
