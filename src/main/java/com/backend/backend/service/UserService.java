package com.backend.backend.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.backend.entities.User;
import com.backend.backend.repository.UsersRepository;

@Service
public class UserService {

    private static final Logger logger = LogManager.getLogger(UserService.class);

    @Autowired
    private UsersRepository usersRepository;
    
    public User addUser(User u) {
        logger.info("Adding new user: " + u);
        return usersRepository.save(u);
    }
}
