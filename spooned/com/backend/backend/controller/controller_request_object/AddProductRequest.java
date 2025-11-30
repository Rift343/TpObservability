package com.backend.backend.controller.controller_request_object;
public class AddProductRequest {
    private java.lang.String name;

    private java.lang.Double price;

    private java.lang.String experationDate;// reçu comme String, désérialisé manuellement


    private java.lang.Long userId;

    // Getters et setters
    /**
     *
     * @return String return the name
     */
    public java.lang.String getName() {
        return name;
    }

    /**
     *
     * @param name
     * 		the name to set
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }

    /**
     *
     * @return Double return the price
     */
    public java.lang.Double getPrice() {
        return price;
    }

    /**
     *
     * @param price
     * 		the price to set
     */
    public void setPrice(java.lang.Double price) {
        this.price = price;
    }

    /**
     *
     * @return Long return the userId
     */
    public java.lang.Long getUserId() {
        return userId;
    }

    /**
     *
     * @param userId
     * 		the userId to set
     */
    public void setUserId(java.lang.Long userId) {
        this.userId = userId;
    }
}
