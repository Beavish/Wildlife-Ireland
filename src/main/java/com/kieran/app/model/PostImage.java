package com.kieran.app.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class PostImage {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long image_id;
	
	private String name;
	 
	private String type;
	
	@Lob
	private byte [] imageByte;
	
	private Long post_id;



	@JsonBackReference
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "post_id", insertable = false, updatable = false, nullable=true)
    private Post post;

}
