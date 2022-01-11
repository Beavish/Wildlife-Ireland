package com.kieran.app.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import static javax.persistence.FetchType.LAZY;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long post_id;

	private String title;

	private String content;

	private Date create_date;
	
    private boolean educational;

	@NotBlank(message = "Username is required")
	private String username;

	private Long userId;

	@JsonBackReference
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "userId", referencedColumnName = "userId", insertable = false, updatable = false, nullable = true)
	private User user;
	
	  @JsonManagedReference
	  @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="post")
	  private List<PostImage> postImage;

}
