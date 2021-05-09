package com.kieran.app.model;

import java.util.Date;

import javax.persistence.Entity;

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
	
	private Long post_id;
	
	private String title;
	
	private String content;
	
	private Date create_date;
	

}