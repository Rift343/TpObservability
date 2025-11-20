package com.backend.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.backend.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);
    

}
