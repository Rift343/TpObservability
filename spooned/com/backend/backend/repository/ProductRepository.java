package com.backend.backend.repository;
public interface ProductRepository extends org.springframework.data.jpa.repository.JpaRepository<com.backend.backend.entities.Product, java.lang.Long> {
    @java.lang.Override
    java.util.List<com.backend.backend.entities.Product> findAll();

    java.util.Optional<com.backend.backend.entities.Product> findById(long id);

    // @Modifying
    // @Query("DELETE FROM Product p WHERE p.id = ?1")
    // void deleteById(long id);
    @org.springframework.data.jpa.repository.Modifying
    @org.springframework.data.jpa.repository.Query("UPDATE Product p SET p.name = ?2, p.price = ?3, p.experationDate = ?4 WHERE p.id = ?1")
    void updateProduct(long id, java.lang.String name, java.lang.Double price, java.sql.Date experationDate);

    @org.springframework.data.jpa.repository.Modifying
    @org.springframework.data.jpa.repository.Query("DELETE FROM Product p WHERE p = ?1")
    void deleteProduct(com.backend.backend.entities.Product product);
}
