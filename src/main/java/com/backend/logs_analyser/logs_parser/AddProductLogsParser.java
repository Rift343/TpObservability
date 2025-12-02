package com.backend.logs_analyser.logs_parser;

public class AddProductLogsParser extends Parser {

    private String name;
    private double price;
    private int userId;
    private String experationDate;

    public AddProductLogsParser(String logLine) {
        //2025-11-30 15:36:50 INFO  ProductService:42 - method addProduct-name=Product_9_40,price=14.159751518110378,experationDate=2022-12-12,userId=112
        //2025-11-30 15:36:50 INFO  ProductService:20 - method addProduct-name=NewProduct,price=49.99,userId=112
        super(logLine);
        String[] paramsParts = this.parameters.split(",");
        for (String param : paramsParts) {
            String[] keyValue = param.split("=");
            switch (keyValue[0]) {
                case "name":
                    this.name = keyValue[1];
                    break;
                case "price":
                    this.price = Double.parseDouble(keyValue[1]);
                    break;
                case "userId":
                    this.userId = Integer.parseInt(keyValue[1]);
                    break;
                case "experationDate":
                    this.experationDate = keyValue[1];
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public int getUserId() {
        return userId;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getExperationDate() {
        return experationDate;
    }

    public void setExperationDate(String experationDate) {
        this.experationDate = experationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return this.price;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("userId=").append(userId);
        sb.append(", name=").append(name);
        sb.append(", price=").append(price);
        return sb.toString();
    }

}
