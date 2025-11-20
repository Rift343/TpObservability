package com.backend.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.backend.backend.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    
    @Autowired
    private UserService userService;

}
