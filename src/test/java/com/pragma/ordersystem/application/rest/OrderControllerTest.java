package com.pragma.ordersystem.application.rest;




import com.pragma.ordersystem.domain.model.OrderStatus;
import com.pragma.ordersystem.domain.model.OrderItem;
import com.pragma.ordersystem.domain.model.CustomerStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.ordersystem.domain.model.Order;
import com.pragma.ordersystem.domain.model.Customer;
import com.pragma.ordersystem.domain.model.Product;
import com.pragma.ordersystem.domain.service.OrderService;
import com.pragma.ordersystem.domain.exception.OrderNotFoundException;
import com.pragma.ordersystem.domain.exception.ProductOutOfStockException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
@DisplayName("Tests para OrderController")
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private OrderService orderService;

    private Order testOrder;
    private Customer testCustomer;

    @BeforeEach
    void setUp() {
        testCustomer = new Customer(
                UUID.randomUUID(),
                "María García",
                "maria@email.com",
                "+0987654321",
                "Avenida Principal 456",
                Customer.CustomerStatus.ACTIVE
        );

        Product testProduct = new Product(
                UUID.randomUUID(),
                "Laptop",
                "Laptop profesional",
                new BigDecimal("1500.00"),
                5,
                "Tecnología",
                true
        );

        List<Order.OrderItem> items = new ArrayList<>();
        items.add(new Order.OrderItem(testProduct, 1));

        testOrder = new Order(
                UUID.randomUUID(),
                testCustomer,
                items,
                Order.OrderStatus.PENDING,
                LocalDateTime.now()
        );
    }

    @Test
    @DisplayName("Crear un pedido - HTTP 201 Created")
    void testCreateOrder_Returns201() throws Exception {
        when(orderService.createOrder(any(Order.class))).thenReturn(testOrder);

        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testOrder)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(testOrder.getId().toString()));

        verify(orderService, times(1)).createOrder(any(Order.class));
    }

    @Test
    @DisplayName("Obtener pedido por ID - HTTP 200 OK")
    void testGetOrderById_Returns200() throws Exception {
        when(orderService.getOrderById(testOrder.getId())).thenReturn(testOrder);

        mockMvc.perform(get("/api/orders/{id}", testOrder.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testOrder.getId().toString()))
                .andExpect(jsonPath("$.status").value("PENDING"));
    }

    @Test
    @DisplayName("Obtener pedido inexistente - HTTP 404 Not Found")
    void testGetOrderById_Returns404() throws Exception {
        UUID nonExistentId = UUID.randomUUID();
        when(orderService.getOrderById(nonExistentId))
                .thenThrow(new OrderNotFoundException("Pedido no encontrado"));

        mockMvc.perform(get("/api/orders/{id}", nonExistentId))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Obtener todos los pedidos - HTTP 200 OK")
    void testGetAllOrders_Returns200() throws Exception {
        when(orderService.getAllOrders()).thenReturn(List.of(testOrder));

        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    @DisplayName("Cancelar un pedido - HTTP 204 No Content")
    void testCancelOrder_Returns204() throws Exception {
        doNothing().when(orderService).cancelOrder(testOrder.getId());

        mockMvc.perform(delete("/api/orders/{id}", testOrder.getId()))
                .andExpect(status().isNoContent());

        verify(orderService, times(1)).cancelOrder(testOrder.getId());
    }

    @Test
    @DisplayName("Actualizar estado de un pedido - HTTP 200 OK")
    void testUpdateOrderStatus_Returns200() throws Exception {
        when(orderService.updateOrderStatus(eq(testOrder.getId()), any()))
                .thenReturn(testOrder);

        mockMvc.perform(patch("/api/orders/{id}/status", testOrder.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("\"COMPLETED\""))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Manejo de error de stock - HTTP 400 Bad Request")
    void testCreateOrder_StockError_Returns400() throws Exception {
        when(orderService.createOrder(any(Order.class)))
                .thenThrow(new ProductOutOfStockException("Stock insuficiente"));

        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testOrder)))
                .andExpect(status().isBadRequest());
    }
}