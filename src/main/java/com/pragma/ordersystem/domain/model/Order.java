package com.pragma.ordersystem.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Order {
    private final UUID id;
    private final Customer customer;
    private final List<OrderItem> items;
    private OrderStatus status;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private BigDecimal totalAmount;

    public Order(UUID id, Customer customer, List<OrderItem> items, OrderStatus status) {
        this.id = id != null ? id : UUID.randomUUID();
        this.customer = validateCustomer(customer);
        this.items = validateItems(items);
        this.status = status != null ? status : OrderStatus.CREATED;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.totalAmount = calculateTotalAmount();
    }

    private Customer validateCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo");
        }
        return customer;
    }

    private List<OrderItem> validateItems(List<OrderItem> items) {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("La lista de items no puede ser nula o vacía");
        }
        return items.stream()
                .peek(item -> {
                    if (item == null) {
                        throw new IllegalArgumentException("Ningún item puede ser nulo");
                    }
                })
                .collect(Collectors.toUnmodifiableList());
    }

    private BigDecimal calculateTotalAmount() {
        return items.stream()
                .map(OrderItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public UUID getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void updateStatus(OrderStatus newStatus) {
        if (newStatus == null) {
            throw new IllegalArgumentException("El nuevo estado no puede ser nulo");
        }
        this.status = newStatus;
        this.updatedAt = LocalDateTime.now();
    }

    public void cancel() {
        if (this.status == OrderStatus.CANCELLED || this.status == OrderStatus.DELIVERED) {
            throw new IllegalStateException("No se puede cancelar un pedido en estado " + this.status);
        }
        this.status = OrderStatus.CANCELLED;
        this.updatedAt = LocalDateTime.now();
    }

    public static class OrderItem {
        private final UUID productId;
        private final String productName;
        private final int quantity;
        private final BigDecimal unitPrice;

        public OrderItem(UUID productId, String productName, int quantity, BigDecimal unitPrice) {
            this.productId = validateProductId(productId);
            this.productName = validateProductName(productName);
            this.quantity = validateQuantity(quantity);
            this.unitPrice = validateUnitPrice(unitPrice);
        }

        private UUID validateProductId(UUID productId) {
            if (productId == null) {
                throw new IllegalArgumentException("El ID del producto no puede ser nulo");
            }
            return productId;
        }

        private String validateProductName(String productName) {
            if (productName == null || productName.trim().isEmpty()) {
                throw new IllegalArgumentException("El nombre del producto no puede ser nulo o vacío");
            }
            return productName;
        }

        private int validateQuantity(int quantity) {
            if (quantity <= 0) {
                throw new IllegalArgumentException("La cantidad debe ser mayor que cero");
            }
            return quantity;
        }

        private BigDecimal validateUnitPrice(BigDecimal unitPrice) {
            if (unitPrice == null || unitPrice.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("El precio unitario debe ser mayor que cero");
            }
            return unitPrice;
        }

        public UUID getProductId() {
            return productId;
        }

        public String getProductName() {
            return productName;
        }

        public int getQuantity() {
            return quantity;
        }

        public BigDecimal getUnitPrice() {
            return unitPrice;
        }

        public BigDecimal getTotalPrice() {
            return unitPrice.multiply(BigDecimal.valueOf(quantity));
        }
    }

    public enum OrderStatus {
        CREATED,
        PROCESSING,
        SHIPPED,
        DELIVERED,
        CANCELLED
    }
}