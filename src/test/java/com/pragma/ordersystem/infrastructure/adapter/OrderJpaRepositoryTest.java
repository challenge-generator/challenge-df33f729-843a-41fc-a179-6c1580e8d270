package com.pragma.ordersystem.infrastructure.adapter;





import com.pragma.ordersystem.infrastructure.entity.OrderItemEntity;
import com.pragma.ordersystem.domain.model.OrderStatus;
import com.pragma.ordersystem.domain.model.OrderItem;
import com.pragma.ordersystem.domain.model.CustomerStatus;
import com.pragma.ordersystem.domain.model.Order;
import com.pragma.ordersystem.domain.model.Customer;
import com.pragma.ordersystem.domain.model.Product;
import com.pragma.ordersystem.infrastructure.entity.OrderEntity;
import com.pragma.ordersystem.infrastructure.entity.CustomerEntity;
import com.pragma.ordersystem.infrastructure.entity.ProductEntity;
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
@DisplayName("Tests para OrderJpaRepository")
class OrderJpaRepositoryTest {

    @Mock
    private OrderJpaRepository orderJpaRepository;

    private Order testOrder;
    private OrderEntity testOrderEntity;
    private Customer testCustomer;
    private Product testProduct;

    @BeforeEach
    void setUp() {
        testCustomer = new Customer(
                UUID.randomUUID(),
                "Carlos López",
                "carlos@email.com",
                "+1122334455",
                "Calle Verde 789",
                Customer.CustomerStatus.ACTIVE
        );

        testProduct = new Product(
                UUID.randomUUID(),
                "Mouse Gamer",
                "Mouse inalámbrico de alta precisión",
                new BigDecimal("45.99"),
                20,
                "Accesorios",
                true
        );

        List<Order.OrderItem> items = new ArrayList<>();
        items.add(new Order.OrderItem(testProduct, 3));

        testOrder = new Order(
                UUID.randomUUID(),
                testCustomer,
                items,
                Order.OrderStatus.PROCESSING,
                LocalDateTime.now()
        );

        CustomerEntity customerEntity = new CustomerEntity(
                testCustomer.getId(),
                testCustomer.getName(),
                testCustomer.getEmail(),
                testCustomer.getPhone(),
                testCustomer.getAddress(),
                testCustomer.getStatus().name()
        );

        ProductEntity productEntity = new ProductEntity(
                testProduct.getId(),
                testProduct.getName(),
                testProduct.getDescription(),
                testProduct.getPrice(),
                testProduct.getStock(),
                testProduct.getCategory(),
                testProduct.isActive()
        );

        List<OrderEntity.OrderItemEntity> itemEntities = new ArrayList<>();
        itemEntities.add(new OrderEntity.OrderItemEntity(productEntity, 3));

        testOrderEntity = new OrderEntity(
                testOrder.getId(),
                customerEntity,
                itemEntities,
                testOrder.getStatus().name(),
                testOrder.getCreatedAt(),
                testOrder.getTotalAmount()
        );
    }

    @Test
    @DisplayName("Guardar un pedido exitosamente")
    void testSave_Success() {
        when(orderJpaRepository.save(any(OrderEntity.class))).thenReturn(testOrderEntity);

        OrderEntity result = orderJpaRepository.save(testOrderEntity);

        assertNotNull(result);
        assertNotNull(result.getId());
        verify(orderJpaRepository, times(1)).save(any(OrderEntity.class));
    }

    @Test
    @DisplayName("Buscar pedido por ID")
    void testFindById_Success() {
        UUID orderId = testOrder.getId();
        when(orderJpaRepository.findById(orderId)).thenReturn(Optional.of(testOrderEntity));

        Optional<OrderEntity> result = orderJpaRepository.findById(orderId);

        assertTrue(result.isPresent());
        assertEquals(orderId, result.get().getId());
    }

    @Test
    @DisplayName("Buscar pedido inexistente retorna vacío")
    void testFindById_NotFound() {
        UUID nonExistentId = UUID.randomUUID();
        when(orderJpaRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        Optional<OrderEntity> result = orderJpaRepository.findById(nonExistentId);

        assertFalse(result.isPresent());
    }

    @Test
    @DisplayName("Buscar todos los pedidos")
    void testFindAll_Success() {
        when(orderJpaRepository.findAll()).thenReturn(List.of(testOrderEntity));

        List<OrderEntity> result = orderJpaRepository.findAll();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    @DisplayName("Buscar pedidos por estado")
    void testFindByStatus_Success() {
        when(orderJpaRepository.findByStatus(Order.OrderStatus.PROCESSING.name()))
                .thenReturn(List.of(testOrderEntity));

        List<OrderEntity> result = orderJpaRepository
                .findByStatus(Order.OrderStatus.PROCESSING.name());

        assertFalse(result.isEmpty());
        assertEquals(Order.OrderStatus.PROCESSING.name(), result.get(0).getStatus());
    }

    @Test
    @DisplayName("Buscar pedidos por cliente")
    void testFindByCustomerId_Success() {
        UUID customerId = testCustomer.getId();
        when(orderJpaRepository.findByCustomerId(customerId))
                .thenReturn(List.of(testOrderEntity));

        List<OrderEntity> result = orderJpaRepository.findByCustomerId(customerId);

        assertFalse(result.isEmpty());
    }

    @Test
    @DisplayName("Eliminar un pedido")
    void testDelete_Success() {
        UUID orderId = testOrder.getId();
        doNothing().when(orderJpaRepository).deleteById(orderId);

        orderJpaRepository.deleteById(orderId);

        verify(orderJpaRepository, times(1)).deleteById(orderId);
    }

    @Test
    @DisplayName("Contar pedidos por estado")
    void testCountByStatus_Success() {
        when(orderJpaRepository.countByStatus(Order.OrderStatus.PENDING.name()))
                .thenReturn(5L);

        Long count = orderJpaRepository.countByStatus(Order.OrderStatus.PENDING.name());

        assertEquals(5L, count);
    }
}