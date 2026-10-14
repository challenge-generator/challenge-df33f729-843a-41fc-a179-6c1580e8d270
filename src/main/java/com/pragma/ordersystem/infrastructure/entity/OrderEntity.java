package com.pragma.ordersystem.infrastructure.entity;

import com.pragma.ordersystem.domain.model.Customer;
import com.pragma.ordersystem.domain.model.Order;
import com.pragma.ordersystem.domain.model.Order.OrderItem;
import com.pragma.ordersystem.domain.model.Order.OrderStatus;
import com.pragma.ordersystem.domain.model.Product;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders", indexes = {
    @Index(name = "idx_order_customer_id", columnList = "customer_id"),
    @Index(name = "idx_order_status", columnList = "status"),
    @Index(name = "idx_order_created_at", columnList = "created_at")
})
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "customer_id", nullable = false)
    private UUID customerId;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "customer_email", nullable = false)
    private String customerEmail;

    @Column(name = "customer_phone")
    private String customerPhone;

    @Column(name = "customer_address")
    private String customerAddress;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private OrderStatus status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "total_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderItemEntity> items = new ArrayList<>();

    public OrderEntity() {
    }

    public OrderEntity(UUID id, UUID customerId, String customerName, String customerEmail,
                       String customerPhone, String customerAddress, OrderStatus status,
                       LocalDateTime createdAt, LocalDateTime updatedAt, BigDecimal totalAmount) {
        this.id = id;
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.customerAddress = customerAddress;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.totalAmount = totalAmount;
    }

    public static OrderEntity fromDomain(Order order) {
        OrderEntity entity = new OrderEntity();
        entity.setId(order.getId());
        entity.setStatus(order.getStatus());
        entity.setCreatedAt(order.getCreatedAt());
        entity.setUpdatedAt(order.getUpdatedAt());
        entity.setTotalAmount(order.getTotalAmount());

        if (order.getCustomer() != null) {
            Customer customer = order.getCustomer();
            entity.setCustomerId(customer.getId());
            entity.setCustomerName(customer.getName());
            entity.setCustomerEmail(customer.getEmail());
            entity.setCustomerPhone(customer.getPhone());
            entity.setCustomerAddress(customer.getAddress());
        }

        if (order.getItems() != null) {
            for (OrderItem item : order.getItems()) {
                OrderItemEntity itemEntity = OrderItemEntity.fromDomain(item);
                itemEntity.setOrder(entity);
                entity.getItems().add(itemEntity);
            }
        }

        return entity;
    }

    public Order toDomain() {
        Customer customer = new Customer(
            customerId,
            customerName,
            customerEmail,
            customerPhone,
            customerAddress,
            null
        );

        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemEntity itemEntity : items) {
            orderItems.add(itemEntity.toDomain());
        }

        Order order = new Order(
            id,
            customer,
            orderItems,
            status
        );

        return order;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<OrderItemEntity> getItems() {
        return items;
    }

    public void setItems(List<OrderItemEntity> items) {
        this.items = items;
    }

    @Entity
    @Table(name = "order_items", indexes = {
        @Index(name = "idx_order_item_order_id", columnList = "order_id"),
        @Index(name = "idx_order_item_product_id", columnList = "product_id")
    })
    public static class OrderItemEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private UUID id;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "order_id", nullable = false)
        private OrderEntity order;

        @Column(name = "product_id", nullable = false)
        private UUID productId;

        @Column(name = "product_name", nullable = false)
        private String productName;

        @Column(nullable = false)
        private Integer quantity;

        @Column(name = "unit_price", nullable = false, precision = 10, scale = 2)
        private BigDecimal unitPrice;

        @Column(name = "subtotal", nullable = false, precision = 10, scale = 2)
        private BigDecimal subtotal;

        public OrderItemEntity() {
        }

        public static OrderItemEntity fromDomain(OrderItem item) {
            OrderItemEntity entity = new OrderItemEntity();
            if (item.getProduct() != null) {
                Product product = item.getProduct();
                entity.setProductId(product.getId());
                entity.setProductName(product.getName());
            }
            entity.setQuantity(item.getQuantity());
            entity.setUnitPrice(item.getUnitPrice());
            entity.setSubtotal(item.getSubtotal());
            return entity;
        }

        public OrderItem toDomain() {
            Product product = new Product(
                productId,
                productName,
                null,
                unitPrice,
                0,
                null,
                true
            );
            return new OrderItem(product, quantity, unitPrice);
        }

        public UUID getId() {
            return id;
        }

        public void setId(UUID id) {
            this.id = id;
        }

        public OrderEntity getOrder() {
            return order;
        }

        public void setOrder(OrderEntity order) {
            this.order = order;
        }

        public UUID getProductId() {
            return productId;
        }

        public void setProductId(UUID productId) {
            this.productId = productId;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public BigDecimal getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(BigDecimal unitPrice) {
            this.unitPrice = unitPrice;
        }

        public BigDecimal getSubtotal() {
            return subtotal;
        }

        public void setSubtotal(BigDecimal subtotal) {
            this.subtotal = subtotal;
        }
    }
}