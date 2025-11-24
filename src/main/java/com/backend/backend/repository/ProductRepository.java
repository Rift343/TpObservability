package com.backend.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.backend.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Override
    List<Product> findAll();

    Product findById(long id);

    @Modifying
    @Query("DELETE FROM Product p WHERE p.id = ?1")
    void deleteById(long id);

    @Modifying
    @Query("UPDATE Product p SET p.name = ?2, p.price = ?3, p.experationDate = ?4 WHERE p.id = ?1")
    void updateProduct(long id, String name, Double price, java.sql.Date experationDate);

    @Modifying
    @Query("DELETE FROM Product p WHERE p = ?1")
    void deleteProduct(Product product);

} 