package com.pragma.ordersystem.domain.exception;


import com.pragma.ordersystem.domain.model.Product;
import java.math.BigDecimal;
import java.util.UUID;

public class ProductOutOfStockException extends RuntimeException {
    private final UUID productId;
    private final String productName;
    private final int requestedQuantity;
    private final int availableStock;
    private final BigDecimal productPrice;
    private final String category;
    private final long timestamp;

    public ProductOutOfStockException(UUID productId, String productName, int requestedQuantity, int availableStock) {
        super(buildMessage(productId, productName, requestedQuantity, availableStock));
        this.productId = productId;
        this.productName = productName;
        this.requestedQuantity = requestedQuantity;
        this.availableStock = availableStock;
        this.productPrice = null;
        this.category = null;
        this.timestamp = System.currentTimeMillis();
    }

    public ProductOutOfStockException(UUID productId, String productName, int requestedQuantity, 
                                       int availableStock, BigDecimal productPrice, String category) {
        super(buildDetailedMessage(productId, productName, requestedQuantity, availableStock, productPrice, category));
        this.productId = productId;
        this.productName = productName;
        this.requestedQuantity = requestedQuantity;
        this.availableStock = availableStock;
        this.productPrice = productPrice;
        this.category = category;
        this.timestamp = System.currentTimeMillis();
    }

    private static String buildMessage(UUID productId, String productName, int requested, int available) {
        return String.format("Product out of stock: ID=%s, Name=%s, Requested=%d, Available=%d",
                productId != null ? productId.toString() : "unknown",
                productName != null ? productName : "unknown",
                requested,
                available);
    }

    private static String buildDetailedMessage(UUID productId, String productName, int requested, 
                                                int available, BigDecimal price, String category) {
        StringBuilder sb = new StringBuilder();
        sb.append("Product out of stock: ");
        sb.append("ID=").append(productId != null ? productId.toString() : "unknown");
        sb.append(", Name=").append(productName != null ? productName : "unknown");
        sb.append(", Requested=").append(requested);
        sb.append(", Available=").append(available);
        if (price != null) {
            sb.append(", Price=").append(price.toString());
        }
        if (category != null) {
            sb.append(", Category=").append(category);
        }
        return sb.toString();
    }

    public UUID getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getRequestedQuantity() {
        return requestedQuantity;
    }

    public int getAvailableStock() {
        return availableStock;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public String getCategory() {
        return category;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getErrorCode() {
        return "PRODUCT_OUT_OF_STOCK";
    }

    public boolean hasEnoughStockFor(int quantity) {
        return availableStock >= quantity;
    }

    public int getShortageAmount() {
        return Math.max(0, requestedQuantity - availableStock);
    }

    public String getDetailedMessage() {
        return String.format("ErrorCode: %s, ProductId: %s, ProductName: %s, Requested: %d, Available: %d, Timestamp: %d",
                getErrorCode(),
                productId != null ? productId.toString() : "null",
                productName,
                requestedQuantity,
                availableStock,
                timestamp);
    }
}