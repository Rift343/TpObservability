package com.backend.logs_analyser.logs_parser;

public class UpdateProductLogsParser extends Parser {
    
    private int id;
    private String name;
    private double price;

    public UpdateProductLogsParser(String logLine) {
        //2025-11-30 15:36:50 INFO  ProductService:30 - method updateProduct-id=12,name=NewProduct,price=99.99,userId=112
        super(logLine);
        String[] paramsParts = this.parameters.split(",");
        for (String param : paramsParts) {
            String[] keyValue = param.split("=");
            switch (keyValue[0]) {
                case "id":
                    this.id = Integer.parseInt(keyValue[1]);
                    break;
                case "name":
                    this.name = keyValue[1];
                    break;
                case "price":
                    this.price = Double.parseDouble(keyValue[1]);
                    break;
                case "userId":
                    this.userId = Integer.parseInt(keyValue[1]);
                    break;
                default:
                    break;
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        sb.append("id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", name=").append(name);
        sb.append(", price=").append(price);
        return sb.toString();
    }



}
