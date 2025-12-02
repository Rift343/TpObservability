package com.backend.logs_analyser.logs_parser;

public class DeleteProductLogsParser extends Parser {

    private int id;
    private int userId;

    public DeleteProductLogsParser(String logLine) {
        //2025-11-30 15:36:50 INFO  ProductService:30 - method deleteProductById-id=12,userId=112
        super(logLine);
        String[] paramsParts = this.parameters.split(",");
        for (String param : paramsParts) {
            String[] keyValue = param.split("=");
            if (keyValue[0].equals("id")) {
                this.id = Integer.parseInt(keyValue[1]);
            } else if (keyValue[0].equals("userId")) {
                this.userId = Integer.parseInt(keyValue[1]);
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Override
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("id=").append(id);
        sb.append(", userId=").append(userId);
        return sb.toString();
    }

    

}
