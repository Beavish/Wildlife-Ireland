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
public class Crime {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long crime_id;
	
	private String description;
	
	private String geo_location;
	
	private Date create_date;
	

}
