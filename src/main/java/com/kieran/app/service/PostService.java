package com.kieran.app.service;


import com.kieran.app.repo.PostRepo;
import com.kieran.app.repo.UserRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PostService {

    private final PostRepo postRepository;
    private final UserRepo userRepository;
    private final AuthService authService;

    public void save() {
  
    }
}