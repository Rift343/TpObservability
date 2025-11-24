package com.backend.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.backend.backend.entities.User;
import com.backend.backend.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User u){
        userService.addUser(u);
        return ResponseEntity.status(HttpStatus.CREATED).body(u);
    }
    

}
