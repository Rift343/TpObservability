package com.backend.backend.entities;
@jakarta.persistence.Entity
@jakarta.persistence.Table(name = "products")
public class Product {
    @jakarta.persistence.Id
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private java.lang.Long id;

    private java.lang.String name;

    private java.lang.Double price;

    private java.sql.Date experationDate;

    public java.lang.Long getId() {
        return id;
    }

    public void setId(java.lang.Long id) {
        this.id = id;
    }

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
     * @return Date return the experationDate
     */
    public java.sql.Date getExperationDate() {
        return experationDate;
    }

    /**
     *
     * @param experationDate
     * 		the experationDate to set
     */
    public void setExperationDate(java.sql.Date experationDate) {
        this.experationDate = experationDate;
    }
}
