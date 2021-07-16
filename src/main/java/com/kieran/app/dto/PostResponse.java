package com.kieran.app.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private Long post_id;
    private String title;
    private String content;
    private String username;
	private Date create_date;
	private boolean educational;



 
}