package com.backend.logs_analyser.userProfile.generator.formalProfile;

public abstract class FormalProfile {

    private long userId;
    private int ADD_PRODUCT;
    private int GET_ALL_PRODUCTS;
    private int UPDATE_PRODUCT;
    private int DELETE_PRODUCT;
    private int GET_PRODUCT_BY_ID;
    
    public FormalProfile(long userId, int aDD_PRODUCT, int gET_ALL_PRODUCTS, int uPDATE_PRODUCT, int dELETE_PRODUCT,
            int gET_PRODUCT_BY_ID) {
        this.userId = userId;
        ADD_PRODUCT = aDD_PRODUCT;
        GET_ALL_PRODUCTS = gET_ALL_PRODUCTS;
        UPDATE_PRODUCT = uPDATE_PRODUCT;
        DELETE_PRODUCT = dELETE_PRODUCT;
        GET_PRODUCT_BY_ID = gET_PRODUCT_BY_ID;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getADD_PRODUCT() {
        return ADD_PRODUCT;
    }

    public void setADD_PRODUCT(int aDD_PRODUCT) {
        ADD_PRODUCT = aDD_PRODUCT;
    }

    public int getGET_ALL_PRODUCTS() {
        return GET_ALL_PRODUCTS;
    }

    public void setGET_ALL_PRODUCTS(int gET_ALL_PRODUCTS) {
        GET_ALL_PRODUCTS = gET_ALL_PRODUCTS;
    }

    public int getUPDATE_PRODUCT() {
        return UPDATE_PRODUCT;
    }

    public void setUPDATE_PRODUCT(int uPDATE_PRODUCT) {
        UPDATE_PRODUCT = uPDATE_PRODUCT;
    }

    public int getDELETE_PRODUCT() {
        return DELETE_PRODUCT;
    }

    public void setDELETE_PRODUCT(int dELETE_PRODUCT) {
        DELETE_PRODUCT = dELETE_PRODUCT;
    }

    public int getGET_PRODUCT_BY_ID() {
        return GET_PRODUCT_BY_ID;
    }

    public void setGET_PRODUCT_BY_ID(int gET_PRODUCT_BY_ID) {
        GET_PRODUCT_BY_ID = gET_PRODUCT_BY_ID;
    }

    

}
