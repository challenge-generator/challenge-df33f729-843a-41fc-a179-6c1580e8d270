package com.pragma.ordersystem.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {
    private final UUID id;
    private final String name;
    private final String description;
    private final BigDecimal price;
    private int stock;
    private final String category;
    private final boolean active;

    public Product(UUID id, String name, String description, BigDecimal price, int stock, String category, boolean active) {
        this.id = id != null ? id : UUID.randomUUID();
        this.name = validateName(name);
        this.description = description != null ? description : "";
        this.price = validatePrice(price);
        this.stock = validateStock(stock);
        this.category = validateCategory(category);
        this.active = active;
    }

    private String validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede ser nulo o vacío");
        }
        return name;
    }

    private BigDecimal validatePrice(BigDecimal price) {
        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor que cero");
        }
        return price;
    }

    private int validateStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
        return stock;
    }

    private String validateCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("La categoría no puede ser nula o vacía");
        }
        return category;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public String getCategory() {
        return category;
    }

    public boolean isActive() {
        return active;
    }

    public void updateStock(int newStock) {
        if (newStock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
        this.stock = newStock;
    }

    public void reserveStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("La cantidad a reservar debe ser mayor que cero");
        }
        if (this.stock < quantity) {
            throw new IllegalStateException("Stock insuficiente para el producto " + this.name);
        }
        this.stock -= quantity;
    }

    public void releaseStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("La cantidad a liberar debe ser mayor que cero");
        }
        this.stock += quantity;
    }
}