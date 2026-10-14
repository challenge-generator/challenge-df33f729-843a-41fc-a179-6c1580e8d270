package com.pragma.ordersystem.domain.service;

import com.pragma.ordersystem.domain.exception.OrderNotFoundException;
import com.pragma.ordersystem.domain.exception.PaymentFailedException;
import com.pragma.ordersystem.domain.exception.ProductOutOfStockException;
import com.pragma.ordersystem.domain.model.Customer;
import com.pragma.ordersystem.domain.model.Order;
import com.pragma.ordersystem.domain.model.Order.OrderStatus;
import com.pragma.ordersystem.domain.model.Order.OrderItem;
import com.pragma.ordersystem.domain.model.Product;
import com.pragma.ordersystem.domain.port.OrderRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class OrderService {
    
    private final OrderRepository orderRepository;
    
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    
    public Order createOrder(Customer customer, List<OrderItem> items) {
        validateItemsNotEmpty(items);
        validateItemsStock(items);
        
        Order order = new Order(
            UUID.randomUUID(),
            customer,
            items,
            OrderStatus.PENDING,
            LocalDateTime.now(),
            null,
            BigDecimal.ZERO
        );
        
        Order savedOrder = orderRepository.save(order);
        reserveStockForItems(items);
        
        return savedOrder;
    }
    
    public Order getOrderById(UUID orderId) {
        return orderRepository.findById(orderId)
            .orElseThrow(() -> new OrderNotFoundException(
                "Pedido no encontrado con ID: " + orderId));
    }
    
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    
    public List<Order> getOrdersByCustomer(UUID customerId) {
        return orderRepository.findByCustomerId(customerId);
    }
    
    public List<Order> getOrdersByStatus(OrderStatus status) {
        return orderRepository.findByStatus(status.name());
    }
    
    public Order updateOrderStatus(UUID orderId, OrderStatus newStatus) {
        Order order = getOrderById(orderId);
        validateStatusTransition(order.getStatus(), newStatus);
        order.updateStatus(newStatus);
        return orderRepository.save(order);
    }
    
    public Order cancelOrder(UUID orderId) {
        Order order = getOrderById(orderId);
        
        if (order.getStatus() == OrderStatus.DELIVERED || 
            order.getStatus() == OrderStatus.CANCELLED) {
            throw new IllegalStateException(
                "No se puede cancelar un pedido en estado: " + order.getStatus());
        }
        
        order.cancel();
        releaseStockForItems(order.getItems());
        return orderRepository.save(order);
    }
    
    public Order confirmPayment(UUID orderId) {
        Order order = getOrderById(orderId);
        
        if (order.getStatus() != OrderStatus.PENDING) {
            throw new PaymentFailedException(
                "El pedido no está en estado pendiente de pago");
        }
        
        order.updateStatus(OrderStatus.CONFIRMED);
        return orderRepository.save(order);
    }
    
    public Order markAsDelivered(UUID orderId) {
        Order order = getOrderById(orderId);
        
        if (order.getStatus() != OrderStatus.SHIPPED) {
            throw new IllegalStateException(
                "El pedido debe estar en estado enviado para marcar como entregado");
        }
        
        order.updateStatus(OrderStatus.DELIVERED);
        return orderRepository.save(order);
    }
    
    public Order markAsShipped(UUID orderId) {
        Order order = getOrderById(orderId);
        
        if (order.getStatus() != OrderStatus.CONFIRMED) {
            throw new IllegalStateException(
                "El pedido debe estar confirmado para ser enviado");
        }
        
        order.updateStatus(OrderStatus.SHIPPED);
        return orderRepository.save(order);
    }
    
    public List<Order> getOrdersByCustomerAndStatus(UUID customerId, OrderStatus status) {
        return orderRepository.findByCustomerIdAndStatus(customerId, status.name());
    }
    
    public List<Order> getRecentOrders(LocalDateTime since) {
        return orderRepository.findOrdersCreatedAfter(since);
    }
    
    public long getTotalOrdersCount() {
        return orderRepository.count();
    }
    
    private void validateItemsNotEmpty(List<OrderItem> items) {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException(
                "El pedido debe contener al menos un artículo");
        }
    }
    
    private void validateItemsStock(List<OrderItem> items) {
        for (OrderItem item : items) {
            Product product = item.getProduct();
            if (product == null) {
                throw new IllegalArgumentException(
                    "El artículo no tiene un producto asociado");
            }
            if (product.getStock() < item.getQuantity()) {
                throw new ProductOutOfStockException(
                    "Stock insuficiente para el producto: " + product.getName() +
                    ". Stock disponible: " + product.getStock() +
                    ", cantidad solicitada: " + item.getQuantity());
            }
        }
    }
    
    private void reserveStockForItems(List<OrderItem> items) {
        for (OrderItem item : items) {
            Product product = item.getProduct();
            product.reserveStock(item.getQuantity());
        }
    }
    
    private void releaseStockForItems(List<OrderItem> items) {
        for (OrderItem item : items) {
            Product product = item.getProduct();
            product.releaseStock(item.getQuantity());
        }
    }
    
    private void validateStatusTransition(OrderStatus currentStatus, OrderStatus newStatus) {
        boolean validTransition = switch (currentStatus) {
            case PENDING -> newStatus == OrderStatus.CONFIRMED || 
                            newStatus == OrderStatus.CANCELLED;
            case CONFIRMED -> newStatus == OrderStatus.SHIPPED || 
                              newStatus == OrderStatus.CANCELLED;
            case SHIPPED -> newStatus == OrderStatus.DELIVERED;
            case DELIVERED, CANCELLED -> false;
        };
        
        if (!validTransition) {
            throw new IllegalStateException(
                "Transición de estado inválida: " + currentStatus + " -> " + newStatus);
        }
    }
}