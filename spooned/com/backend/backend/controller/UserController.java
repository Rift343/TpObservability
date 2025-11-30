package com.backend.backend.controller;
@org.springframework.web.bind.annotation.RestController
@org.springframework.web.bind.annotation.RequestMapping("/user")
public class UserController {
    @org.springframework.beans.factory.annotation.Autowired
    private com.backend.backend.service.UserService userService;

    @org.springframework.web.bind.annotation.PostMapping("/add")
    public org.springframework.http.ResponseEntity<com.backend.backend.entities.User> addUser(@org.springframework.web.bind.annotation.RequestBody
    com.backend.backend.entities.User u) {
        userService.addUser(u);
        return org.springframework.http.ResponseEntity.status(org.springframework.http.HttpStatus.CREATED).body(u);
    }
}
