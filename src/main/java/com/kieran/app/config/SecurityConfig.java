package com.kieran.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.kieran.app.security.JwtAuthenticationFilter;

import org.springframework.security.config.BeanIds;

import lombok.AllArgsConstructor;

import org.springframework.security.core.userdetails.UserDetailsService;

@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final UserDetailsService userDetailsService;

	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter();
	}

	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity.cors().disable();
		httpSecurity.csrf()	
					.disable()
					.authorizeRequests()
					.antMatchers("/api/auth/**", "/api/post/**","/api/record/new", "/api/crime/new", "/api/record/all","/api/image/new","/api/postimage/new")
					.permitAll()
					.antMatchers(HttpMethod.GET, "/api/posts/")
	                .permitAll()
	                .antMatchers(HttpMethod.GET, "/api/posts/**")
	                .permitAll()
					.anyRequest()
					.authenticated();
		
	
		
		
		httpSecurity.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		
		
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();

	}
}
