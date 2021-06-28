package com.kieran.app.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {
    private Long post_id;
    private String title;
    private String content;
    private String username;
	private Date create_date;


 
}