package com.backend.backend.service;
@org.springframework.stereotype.Service
public class ProductService {
    private static final org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getLogger(ProductService.class);

    @org.springframework.beans.factory.annotation.Autowired
    private com.backend.backend.repository.ProductRepository productRepository;

    // fetch all products
    public java.util.List<com.backend.backend.entities.Product> getAllProducts(long userId) {
        logger.info("method getAllProducts-userId=" + userId + "");
        return productRepository.findAll();
    }

    // fetch product by id
    public java.util.Optional<com.backend.backend.entities.Product> getProductById(long id, long userId) {
        logger.info("method getProductById-id=" + id + ",userId=" + userId + "");
        java.util.Optional<com.backend.backend.entities.Product> product = productRepository.findById(id);
        try {
            product.get();
        } catch (java.lang.Exception e) {
            product = java.util.Optional.empty();
        }
        return product;
    }

    // delete product by id
    @jakarta.transaction.Transactional
    public void deleteProductById(long id, long userId) {
        logger.info("method deleteProductById-id=" + id + ",userId=" + userId + "");
        productRepository.deleteById(id);
    }

    // update product
    @jakarta.transaction.Transactional
    public void updateProduct(long id, java.lang.String name, java.lang.Double price, java.sql.Date experationDate, long userId) {
        logger.info("method updateProduct-id=" + id + ",name=" + name + ",price=" + price + ",experationDate=" + experationDate + ",userId=" + userId + "");
        productRepository.updateProduct(id, name, price, experationDate);
    }

    public void addProduct(java.lang.String name, java.lang.Double price, java.sql.Date experationDate, long userId) {
        logger.info("method addProduct-name=" + name + ",price=" + price + ",experationDate=" + experationDate + ",userId=" + userId + "");
        com.backend.backend.entities.Product product = new com.backend.backend.entities.Product();
        product.setName(name);
        product.setPrice(price);
        product.setExperationDate(experationDate);
        productRepository.save(product);
    }
}
