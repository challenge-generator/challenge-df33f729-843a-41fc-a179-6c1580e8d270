package com.pragma.ordersystem.domain.service;




import com.pragma.ordersystem.domain.model.OrderStatus;
import com.pragma.ordersystem.domain.model.OrderItem;
import com.pragma.ordersystem.domain.model.CustomerStatus;
import com.pragma.ordersystem.domain.model.Order;
import com.pragma.ordersystem.domain.model.Customer;
import com.pragma.ordersystem.domain.model.Product;
import com.pragma.ordersystem.domain.port.OrderRepository;
import com.pragma.ordersystem.domain.exception.OrderNotFoundException;
import com.pragma.ordersystem.domain.exception.ProductOutOfStockException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests para OrderService")
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    private Order testOrder;
    private Customer testCustomer;
    private Product testProduct;

    @BeforeEach
    void setUp() {
        testCustomer = new Customer(
                UUID.randomUUID(),
                "Juan Pérez",
                "juan@email.com",
                "+1234567890",
                "Calle 123",
                Customer.CustomerStatus.ACTIVE
        );

        testProduct = new Product(
                UUID.randomUUID(),
                "Producto Test",
                "Descripción del producto",
                new BigDecimal("99.99"),
                10,
                "Electrónica",
                true
        );

        List<Order.OrderItem> items = new ArrayList<>();
        items.add(new Order.OrderItem(testProduct, 2));

        testOrder = new Order(
                UUID.randomUUID(),
                testCustomer,
                items,
                Order.OrderStatus.PENDING,
                LocalDateTime.now()
        );
    }

    @Test
    @DisplayName("Crear un pedido exitosamente")
    void testCreateOrder_Success() {
        when(orderRepository.save(any(Order.class))).thenReturn(testOrder);

        Order result = orderService.createOrder(testOrder);

        assertNotNull(result);
        assertEquals(Order.OrderStatus.PENDING, result.getStatus());
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    @DisplayName("Obtener un pedido por ID exitosamente")
    void testGetOrderById_Success() {
        UUID orderId = testOrder.getId();
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(testOrder));

        Order result = orderService.getOrderById(orderId);

        assertNotNull(result);
        assertEquals(orderId, result.getId());
    }

    @Test
    @DisplayName("Lanzar excepción cuando el pedido no existe")
    void testGetOrderById_NotFound() {
        UUID orderId = UUID.randomUUID();
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class, () -> orderService.getOrderById(orderId));
    }

    @Test
    @DisplayName("Cancelar un pedido exitosamente")
    void testCancelOrder_Success() {
        when(orderRepository.findById(testOrder.getId())).thenReturn(Optional.of(testOrder));
        when(orderRepository.save(any(Order.class))).thenReturn(testOrder);

        orderService.cancelOrder(testOrder.getId());

        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    @DisplayName("Actualizar estado de un pedido")
    void testUpdateOrderStatus() {
        when(orderRepository.findById(testOrder.getId())).thenReturn(Optional.of(testOrder));
        when(orderRepository.save(any(Order.class))).thenReturn(testOrder);

        orderService.updateOrderStatus(testOrder.getId(), Order.OrderStatus.COMPLETED);

        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    @DisplayName("Obtener todos los pedidos")
    void testGetAllOrders() {
        List<Order> orders = List.of(testOrder);
        when(orderRepository.findAll()).thenReturn(orders);

        List<Order> result = orderService.getAllOrders();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    @DisplayName("Verificar manejo de stock insuficiente")
    void testCreateOrder_InsufficientStock() {
        Product outOfStockProduct = new Product(
                UUID.randomUUID(),
                "Producto sin stock",
                "Descripción",
                new BigDecimal("50.00"),
                0,
                "Categoría",
                true
        );

        when(orderRepository.save(any(Order.class)))
                .thenThrow(new ProductOutOfStockException("Stock insuficiente"));

        assertThrows(ProductOutOfStockException.class, 
                () -> orderService.createOrder(testOrder));
    }
}