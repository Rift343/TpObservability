package com.backend.logs_analyser.logs_parser;


public class GetALLproductLogsParser extends Parser {
    private int userId;

    public GetALLproductLogsParser(String logLine){
        //2025-11-30 15:36:50 INFO  ProductService:11 - method getAllProducts-userId=112
        super(logLine);

        String[] paramsParts = this.parameters.split(",");
        for (String param : paramsParts) {
            String[] keyValue = param.split("=");
            if (keyValue[0].equals("userId")) {
                this.userId = Integer.parseInt(keyValue[1]);
            }
        }
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
        sb.append("userId=").append(this.getUserId());
        return sb.toString();
    }

}