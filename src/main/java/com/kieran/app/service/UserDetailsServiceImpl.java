package com.kieran.app.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kieran.app.model.User;
import com.kieran.app.repo.UserRepo;

import lombok.AllArgsConstructor;
import static java.util.Collections.singletonList;


@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService{

	  private final UserRepo userRepo;

	    @Override
	    @Transactional(readOnly = true)
	    public UserDetails loadUserByUsername(String username) {
	        Optional<User> userOptional = userRepo.findByUsername(username);
	        User user = userOptional
	                .orElseThrow(() -> new UsernameNotFoundException("No user " +
	                        "Found with username : " + username));

	        return new org.springframework.security
	                .core.userdetails.User(user.getUsername(), user.getPassword(),
	                user.isEnabled(), true, true,
	                true, getAuthorities("USER"));
	    }

	    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
	        return singletonList(new SimpleGrantedAuthority(role));
	    }

}
