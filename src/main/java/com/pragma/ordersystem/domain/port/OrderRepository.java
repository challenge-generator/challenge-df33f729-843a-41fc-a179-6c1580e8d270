package com.pragma.ordersystem.domain.port;

import com.pragma.ordersystem.domain.model.Order;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {
    
    Order save(Order order);
    
    Optional<Order> findById(UUID id);
    
    List<Order> findAll();
    
    List<Order> findByCustomerId(UUID customerId);
    
    List<Order> findByStatus(String status);
    
    void delete(UUID id);
    
    boolean existsById(UUID id);
    
    long count();
    
    List<Order> findByCustomerIdAndStatus(UUID customerId, String status);
    
    List<Order> findOrdersCreatedAfter(java.time.LocalDateTime dateTime);
}