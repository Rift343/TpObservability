package com.backend.logs_analyser.logs_parser;

public abstract class Parser {

    String TimeStamp;
    String LogLevel;
    String className;
    String methodName;
    String parameters;
    int userId;


    public Parser(String logLine) {
        //2025-11-30 15:36:50 INFO  ProductService:30 - method deleteProductById-id=12,userId=112
        String[] parts = logLine.split(" - ");
        String[] headerParts = parts[0].split(" ");
        this.TimeStamp = headerParts[0] + " " + headerParts[1];
        this.LogLevel = headerParts[2];
        String[] classMethodParts = headerParts[3].split(":");
        this.className = classMethodParts[0];
        this.methodName = parts[1].split("-")[0].replace("method ", "");
        this.parameters = parts[1].substring(parts[1].indexOf("-") + 1);
    }
    
    public boolean isService(){
        return this.className.contains("Service");
    }

    public String getTimeStamp() {
        return TimeStamp;
    }
    public void setTimeStamp(String timeStamp) {
        TimeStamp = timeStamp;
    }
    public String getLogLevel() {
        return LogLevel;
    }
    public void setLogLevel(String logLevel) {
        LogLevel = logLevel;
    }
    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }
    public String getMethodName() {
        return methodName;
    }
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public int getUserId() {
        return userId;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Parser{");
        sb.append("TimeStamp=").append(TimeStamp);
        sb.append(", LogLevel=").append(LogLevel);
        sb.append(", className=").append(className);
        sb.append(", methodName=").append(methodName);
        sb.append(", parameters=").append(parameters);
        return sb.toString();
    }


    


}
