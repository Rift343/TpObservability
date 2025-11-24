package com.backend.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import java.util.List;

import com.backend.backend.service.ProductService;
import com.backend.backend.entities.Product;    

@Controller
@RequestMapping("/product")
public class ProductController {
 
    @Autowired
    private ProductService productService;

    //get all products in the bodyResponseEntity<User>
    @RequestMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    };

    @RequestMapping("/get/{id}")
    public ResponseEntity<Product> getProductById(long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    };

    @RequestMapping("/delete/{id}")
    public void deleteProductById(long id) {
        productService.deleteProductById(id);
    };

    @RequestMapping("/update/{id}")
    public void updateProduct(long id, String name, Double price, java.sql.Date experationDate) {
        productService.updateProduct(id, name, price, experationDate);
    };

}
