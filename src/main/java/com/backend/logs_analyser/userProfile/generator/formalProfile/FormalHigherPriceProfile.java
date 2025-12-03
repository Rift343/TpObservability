package com.backend.logs_analyser.userProfile.generator.formalProfile;


import java.util.List;

public class FormalHigherPriceProfile {
    
    private long userId;
    private searchedProduct HigherPriceSearchedProducts;

    public FormalHigherPriceProfile(long userId, List<searchedProduct> allProductsResearched) {
        this.userId = userId;
        searchedProduct highestPriceProduct = allProductsResearched.get(0);
        for (searchedProduct searchedProduct : allProductsResearched) {
            if (searchedProduct.getPrice() > highestPriceProduct.getPrice()) {
                highestPriceProduct = searchedProduct;
            }
        }
        this.HigherPriceSearchedProducts = highestPriceProduct;
    }

    public long getUserId() {
        return userId;
    }

    public searchedProduct HigherPriceSearchedProducts() {
        return HigherPriceSearchedProducts;
    }

    public double getHighestPrice(){
        return HigherPriceSearchedProducts.getPrice();
    }
}
