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
public class Post {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long post_id;
	
	private String title;
	
	private String content;
	
	private Date create_date;
	

}
