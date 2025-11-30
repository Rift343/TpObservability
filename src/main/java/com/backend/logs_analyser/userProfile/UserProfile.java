package com.backend.logs_analyser.userProfile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.backend.logs_analyser.logs_parser.AddProductLogsParser;
import com.backend.logs_analyser.logs_parser.DeleteProductLogsParser;
import com.backend.logs_analyser.logs_parser.GetALLproductLogsParser;
import com.backend.logs_analyser.logs_parser.GetProductByidParser;
import com.backend.logs_analyser.logs_parser.Parser;
import com.backend.logs_analyser.logs_parser.UpdateProductLogsParser;

public class UserProfile {
    private Long userId;
    private List<LogEvent> events = new ArrayList<>();
    private Map<String, Integer> actionCounts = new HashMap<>();

    public UserProfile(Long userID) {
        this.userId = userID;
        actionCounts.put("ADD_PRODUCT", 0);
        actionCounts.put("GET_ALL_PRODUCTS", 0);
        actionCounts.put("UPDATE_PRODUCT", 0);
        actionCounts.put("DELETE_PRODUCT", 0);
        actionCounts.put("GET_PRODUCT_BY_ID", 0);
    }
    public Long getUserId() {
        return userId;
    }

    public List<LogEvent> getEvents() {
        return events;
    }

    public Map<String, Integer> getActionCounts() {
        return actionCounts;
    }

    public void addEvent(LogEvent event) {
        events.add(event);
        switch (event.event) {
            case "ADD_PRODUCT":
                actionCounts.put("ADD_PRODUCT", actionCounts.get("ADD_PRODUCT") + 1);
                break;
            case "GET_ALL_PRODUCTS":
                actionCounts.put("GET_ALL_PRODUCTS", actionCounts.get("GET_ALL_PRODUCTS") + 1);
                break;
            case "UPDATE_PRODUCT":
                actionCounts.put("UPDATE_PRODUCT", actionCounts.get("UPDATE_PRODUCT") + 1);
                break;
            case "DELETE_PRODUCT":
                actionCounts.put("DELETE_PRODUCT", actionCounts.get("DELETE_PRODUCT") + 1);
                break;
            case "GET_PRODUCT_BY_ID":
                actionCounts.put("GET_PRODUCT_BY_ID", actionCounts.get("GET_PRODUCT_BY_ID") + 1);
                break;
            default:
                break;
        }
    }
    public void updateProfile(Parser log) {
    LogEvent event = new LogEvent();
    event.timestamp = log.getTimeStamp();

    switch (log.getMethodName()) {
        case "addProduct":
            event.event = "ADD_PRODUCT";
            if (log instanceof AddProductLogsParser addParser) {
            }
            break;

        case "getAllProducts":
            event.event = "GET_ALL_PRODUCTS";
            break;

        case "updateProduct":
            event.event = "UPDATE_PRODUCT";
            if (log instanceof UpdateProductLogsParser updateParser) {
            }
            break;

        case "deleteProductById":
            event.event = "DELETE_PRODUCT";
            if (log instanceof DeleteProductLogsParser deleteParser) {
            }
            break;

        case "getProductById":
            event.event = "GET_PRODUCT_BY_ID";
            if (log instanceof GetProductByidParser getParser) {
                event.id = getParser.getProductId();
            }
            break;

        default:
            event.event = "UNKNOWN";
            break;
    }

    this.addEvent(event);
}

}

class LogEvent {
    public String timestamp;
    public String event;
    public int id;     

}