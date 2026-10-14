package com.pragma.ordersystem.infrastructure.adapter;


import com.pragma.ordersystem.infrastructure.entity.OrderItemEntity;
import com.pragma.ordersystem.domain.model.Customer;
import com.pragma.ordersystem.domain.model.Customer.CustomerStatus;
import com.pragma.ordersystem.domain.model.Order;
import com.pragma.ordersystem.domain.model.Order.OrderItem;
import com.pragma.ordersystem.domain.model.Order.OrderStatus;
import com.pragma.ordersystem.domain.model.Product;
import com.pragma.ordersystem.domain.port.OrderRepository;
import com.pragma.ordersystem.infrastructure.entity.OrderEntity;
import com.pragma.ordersystem.infrastructure.entity.CustomerEntity;
import com.pragma.ordersystem.infrastructure.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class OrderJpaRepository extends JpaRepository<OrderEntity, UUID> implements OrderRepository {
    
    @Override
    public Order save(Order order) {
        OrderEntity entity = toEntity(order);
        OrderEntity savedEntity = save(entity);
        return toDomain(savedEntity);
    }
    
    @Override
    public Optional<Order> findById(UUID id) {
        return findById(id).map(this::toDomain);
    }
    
    @Override
    public List<Order> findAll() {
        return findAll().stream()
            .map(this::toDomain)
            .collect(Collectors.toList());
    }
    
    @Override
    public List<Order> findByCustomerId(UUID customerId) {
        return findAll().stream()
            .filter(order -> order.getCustomer() != null && 
                           order.getCustomer().getId().equals(customerId))
            .map(this::toDomain)
            .collect(Collectors.toList());
    }
    
    @Override
    public List<Order> findByStatus(String status) {
        return findAll().stream()
            .filter(order -> order.getStatus().name().equals(status))
            .map(this::toDomain)
            .collect(Collectors.toList());
    }
    
    @Override
    public void delete(UUID id) {
        deleteById(id);
    }
    
    @Override
    public boolean existsById(UUID id) {
        return existsById(id);
    }
    
    @Override
    public long count() {
        return count();
    }
    
    @Override
    public List<Order> findByCustomerIdAndStatus(UUID customerId, String status) {
        return findAll().stream()
            .filter(order -> order.getCustomer() != null && 
                           order.getCustomer().getId().equals(customerId) &&
                           order.getStatus().name().equals(status))
            .map(this::toDomain)
            .collect(Collectors.toList());
    }
    
    @Override
    public List<Order> findOrdersCreatedAfter(LocalDateTime dateTime) {
        return findAll().stream()
            .filter(order -> order.getCreatedAt().isAfter(dateTime))
            .map(this::toDomain)
            .collect(Collectors.toList());
    }
    
    private OrderEntity toEntity(Order order) {
        OrderEntity entity = new OrderEntity();
        entity.setId(order.getId());
        entity.setStatus(order.getStatus().name());
        entity.setCreatedAt(order.getCreatedAt());
        entity.setUpdatedAt(order.getUpdatedAt());
        entity.setTotalAmount(order.getTotalAmount());
        
        if (order.getCustomer() != null) {
            CustomerEntity customerEntity = new CustomerEntity();
            customerEntity.setId(order.getCustomer().getId());
            customerEntity.setName(order.getCustomer().getName());
            customerEntity.setEmail(order.getCustomer().getEmail());
            customerEntity.setPhone(order.getCustomer().getPhone());
            customerEntity.setAddress(order.getCustomer().getAddress());
            customerEntity.setStatus(order.getCustomer().getStatus().name());
            entity.setCustomer(customerEntity);
        }
        
        List<OrderEntity.OrderItemEntity> itemEntities = new ArrayList<>();
        if (order.getItems() != null) {
            for (OrderItem item : order.getItems()) {
                OrderEntity.OrderItemEntity itemEntity = new OrderEntity.OrderItemEntity();
                itemEntity.setQuantity(item.getQuantity());
                itemEntity.setUnitPrice(item.getUnitPrice());
                itemEntity.setSubtotal(item.getSubtotal());
                
                if (item.getProduct() != null) {
                    ProductEntity productEntity = new ProductEntity();
                    productEntity.setId(item.getProduct().getId());
                    productEntity.setName(item.getProduct().getName());
                    productEntity.setDescription(item.getProduct().getDescription());
                    productEntity.setPrice(item.getProduct().getPrice());
                    productEntity.setStock(item.getProduct().getStock());
                    productEntity.setCategory(item.getProduct().getCategory());
                    productEntity.setActive(item.getProduct().isActive());
                    itemEntity.setProduct(productEntity);
                }
                
                itemEntities.add(itemEntity);
            }
        }
        entity.setItems(itemEntities);
        
        return entity;
    }
    
    private Order toDomain(OrderEntity entity) {
        Customer customer = null;
        if (entity.getCustomer() != null) {
            CustomerEntity ce = entity.getCustomer();
            customer = new Customer(
                ce.getId(),
                ce.getName(),
                ce.getEmail(),
                ce.getPhone(),
                ce.getAddress(),
                CustomerStatus.valueOf(ce.getStatus())
            );
        }
        
        List<OrderItem> items = new ArrayList<>();
        if (entity.getItems() != null) {
            for (OrderEntity.OrderItemEntity itemEntity : entity.getItems()) {
                Product product = null;
                if (itemEntity.getProduct() != null) {
                    ProductEntity pe = itemEntity.getProduct();
                    product = new Product(
                        pe.getId(),
                        pe.getName(),
                        pe.getDescription(),
                        pe.getPrice(),
                        pe.getStock(),
                        pe.getCategory(),
                        pe.isActive()
                    );
                }
                
                OrderItem item = new OrderItem(
                    itemEntity.getQuantity(),
                    product,
                    itemEntity.getUnitPrice()
                );
                items.add(item);
            }
        }
        
        Order order = new Order(
            entity.getId(),
            customer,
            items,
            OrderStatus.valueOf(entity.getStatus()),
            entity.getCreatedAt(),
            entity.getUpdatedAt(),
            entity.getTotalAmount()
        );
        
        return order;
    }
}