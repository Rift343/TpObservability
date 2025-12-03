package com.backend.logs_analyser.userProfile.generator.formalProfile;

public class searchedProduct {
    private long productId;
    private double price;
    
    public searchedProduct(long productId, double price) {
        this.productId = productId;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public long getProductId() {
        return productId;
    }

    

    
}
