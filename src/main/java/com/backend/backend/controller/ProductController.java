package com.backend.backend.controller;
@org.springframework.web.bind.annotation.RestController
@org.springframework.web.bind.annotation.RequestMapping("/product")
public class ProductController {
    @org.springframework.beans.factory.annotation.Autowired
    private com.backend.backend.service.ProductService productService;

    // get all products in the bodyResponseEntity<User>
    @org.springframework.web.bind.annotation.RequestMapping("/all")
    public org.springframework.http.ResponseEntity<java.util.List<com.backend.backend.entities.Product>> getAllProducts(@org.springframework.web.bind.annotation.RequestBody
    com.backend.backend.controller.controller_request_object.GetAllProductsRequest request) {
        return org.springframework.http.ResponseEntity.ok(productService.getAllProducts(request.getUserId()));
    }

    @org.springframework.web.bind.annotation.RequestMapping("/get")
    public org.springframework.http.ResponseEntity<com.backend.backend.entities.Product> getProductById(@org.springframework.web.bind.annotation.RequestBody
    com.backend.backend.controller.controller_request_object.GetProductRequest request) {
        if (request == null || request.getId() == null || request.getUserId() == null) {
            return org.springframework.http.ResponseEntity.badRequest().build();
        }

        java.util.Optional<com.backend.backend.entities.Product> product = productService.getProductById(request.getId(), request.getUserId());
        if (product.isEmpty()) {
            return org.springframework.http.ResponseEntity.notFound().build();
        }

        return org.springframework.http.ResponseEntity.ok(product.get());
    }

    @org.springframework.web.bind.annotation.RequestMapping("/delete")
    public void deleteProductById(@org.springframework.web.bind.annotation.RequestBody
    com.backend.backend.controller.controller_request_object.DeleteProductRequest request) {
        productService.deleteProductById(request.getId(), request.getUserId());
    }

    @org.springframework.web.bind.annotation.RequestMapping("/update")
    public void updateProduct(@org.springframework.web.bind.annotation.RequestBody
    com.backend.backend.controller.controller_request_object.UpdateProductRequest request) {
        // désérialisation de la date
        java.sql.Date sqlDate = java.sql.Date.valueOf(request.getExperationDate());
        productService.updateProduct(request.getId(), request.getName(), request.getPrice(), sqlDate, request.getUserId());
    }

    @org.springframework.web.bind.annotation.RequestMapping("/add")
    public void addProduct(@org.springframework.web.bind.annotation.RequestBody
    com.backend.backend.controller.controller_request_object.UpdateProductRequest request) {
        java.sql.Date sqlDate = java.sql.Date.valueOf(request.getExperationDate());
        productService.addProduct(request.getName(), request.getPrice(), sqlDate, request.getUserId());
    }
}
