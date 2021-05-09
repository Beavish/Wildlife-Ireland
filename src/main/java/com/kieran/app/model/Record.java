package com.kieran.app.model;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Record {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long record_id;
	
	private Date  create_date;
	
	private Boolean plant;
	
	private Boolean animal;
	
	private String name;
	
	private int quantity;
	
	private String geo_location;
	
}
