package com.backend.backend.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.backend.backend.entities.Product;
import com.backend.backend.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private static final Logger logger = LogManager.getLogger(ProductService.class);

    @Autowired 
    private ProductRepository productRepository;

    //fetch all products
    public List<Product> getAllProducts() {
        logger.info("fetch all products in the database");
        return productRepository.findAll();
    };

    //fetch product by id
    public Product getProductById(long id) {
        logger.info("fetch product with id: " + id);
        return productRepository.findById(id);
    };

    //delete product by id
    public void deleteProductById(long id) {
        logger.info("delete product with id:" + id);
        productRepository.deleteById(id);
    };

    //update product
    public void updateProduct(long id, String name, Double price, java.sql.Date experationDate) {
        logger.info("update product with id: " + id + " to name: " + name + ", price: " + price + ", experationDate: " + experationDate);
        productRepository.updateProduct(id, name, price, experationDate);
    };

}
