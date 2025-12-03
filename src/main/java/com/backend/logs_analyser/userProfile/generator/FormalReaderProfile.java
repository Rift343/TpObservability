package com.backend.logs_analyser.userProfile.generator;

public class FormalReaderProfile extends FormalProfile {

    int readAction;

    public FormalReaderProfile(long userId, int aDD_PRODUCT, int gET_ALL_PRODUCTS, int uPDATE_PRODUCT, int dELETE_PRODUCT, int gET_PRODUCT_BY_ID) {
        super(userId, aDD_PRODUCT, gET_ALL_PRODUCTS, uPDATE_PRODUCT, dELETE_PRODUCT, gET_PRODUCT_BY_ID);
        this.readAction = gET_ALL_PRODUCTS + gET_PRODUCT_BY_ID;
    }

    public int getReadAction() {
        return readAction;
    }

}
