package com.kieran.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class WildlifePlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(WildlifePlatformApplication.class, args);
	}

}
