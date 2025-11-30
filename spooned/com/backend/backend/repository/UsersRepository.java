package com.backend.backend.repository;
public interface UsersRepository extends org.springframework.data.jpa.repository.JpaRepository<com.backend.backend.entities.User, java.lang.Long> {
    com.backend.backend.entities.User findByName(java.lang.String name);
}
