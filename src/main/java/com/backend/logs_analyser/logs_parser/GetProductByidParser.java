package com.backend.logs_analyser.logs_parser;

public class GetProductByidParser extends Parser {
    private int productId;
    private int userId;

    public GetProductByidParser(String logLine) {
        //2025-11-30 15:36:50 INFO  ProductService:20 - method getProductById-productId=10,userId=112
        super(logLine);
        String[] paramsParts = this.parameters.split(",");
        for (String param : paramsParts) {
            String[] keyValue = param.split("=");
            if (keyValue[0].equals("id")) {
                this.productId = Integer.parseInt(keyValue[1]);
            } else if (keyValue[0].equals("userId")) {
                this.userId = Integer.parseInt(keyValue[1]);
            }
        }
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("productId=").append(productId);
        sb.append(", userId=").append(userId);
        return sb.toString();
    }

}
