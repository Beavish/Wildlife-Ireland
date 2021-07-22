package com.kieran.app.model;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long record_id;
	
	private Date  create_date;
	
	private Boolean plant;
	
	private Boolean animal;
	
	private String name;
	
	private int quantity;
	
	private String geo_location;
	
	  @JsonManagedReference
	  @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="record")
	  private List<Image> image;
	
}
