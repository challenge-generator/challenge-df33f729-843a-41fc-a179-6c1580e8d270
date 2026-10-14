package com.pragma.ordersystem.domain.exception;


import com.pragma.ordersystem.domain.model.Order;
import java.util.UUID;

public class OrderNotFoundException extends RuntimeException {
    private final UUID orderId;
    private final String searchCriteria;
    private final long timestamp;

    public OrderNotFoundException(UUID orderId) {
        super(buildMessage(orderId, null));
        this.orderId = orderId;
        this.searchCriteria = orderId.toString();
        this.timestamp = System.currentTimeMillis();
    }

    public OrderNotFoundException(String message) {
        super(message);
        this.orderId = null;
        this.searchCriteria = message;
        this.timestamp = System.currentTimeMillis();
    }

    public OrderNotFoundException(UUID orderId, String searchCriteria) {
        super(buildMessage(orderId, searchCriteria));
        this.orderId = orderId;
        this.searchCriteria = searchCriteria;
        this.timestamp = System.currentTimeMillis();
    }

    private static String buildMessage(UUID orderId, String criteria) {
        if (criteria != null && !criteria.equals(orderId != null ? orderId.toString() : "")) {
            return "Order not found with criteria: " + criteria;
        }
        return "Order not found with ID: " + (orderId != null ? orderId.toString() : "unknown");
    }

    public UUID getOrderId() {
        return orderId;
    }

    public String getSearchCriteria() {
        return searchCriteria;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getErrorCode() {
        return "ORDER_NOT_FOUND";
    }

    public String getDetailedMessage() {
        return String.format("ErrorCode: %s, OrderId: %s, Criteria: %s, Timestamp: %d",
                getErrorCode(),
                orderId != null ? orderId.toString() : "null",
                searchCriteria,
                timestamp);
    }
}