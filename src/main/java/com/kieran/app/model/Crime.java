package com.kieran.app.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long crime_id;
	
	private String description;
	
	private String geo_location;
	
	private Date create_date;
	
	private String reporter_email;
	
	private String reporter_ip;
	
	
	

}
