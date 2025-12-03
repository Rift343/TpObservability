package com.backend.logs_analyser.userProfile.generator;

public class FormalWriterProfile extends FormalProfile {

    int writeAction;

    public FormalWriterProfile(long userId, int aDD_PRODUCT, int gET_ALL_PRODUCTS, int uPDATE_PRODUCT,int dELETE_PRODUCT, int gET_PRODUCT_BY_ID) {
        super(userId, aDD_PRODUCT, gET_ALL_PRODUCTS, uPDATE_PRODUCT, dELETE_PRODUCT, gET_PRODUCT_BY_ID);
        this.writeAction = aDD_PRODUCT + uPDATE_PRODUCT + dELETE_PRODUCT; 
        
    }

    public int getWriteAction() {
        return writeAction;
    }

    

    

    

}
