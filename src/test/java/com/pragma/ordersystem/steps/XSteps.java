package com.pragma.ordersystem.steps;


import com.pragma.ordersystem.domain.model.OrderItem;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.pragma.ordersystem.domain.model.Order;
import com.pragma.ordersystem.domain.model.Order.OrderStatus;
import com.pragma.ordersystem.domain.model.Customer;
import com.pragma.ordersystem.domain.model.Customer.CustomerStatus;
import com.pragma.ordersystem.domain.model.Product;
import com.pragma.ordersystem.domain.service.OrderService;
import com.pragma.ordersystem.domain.exception.OrderNotFoundException;
import com.pragma.ordersystem.domain.exception.ProductOutOfStockException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class XSteps {

    @Autowired
    private OrderService orderService;

    private Order ordenCreada;
    private Customer clientePrueba;
    private Product productoPrueba;
    private List<Order.OrderItem> itemsPedido;
    private Exception exceptionCapturada;

    @Given("un cliente con datos válidos")
    public void un_cliente_con_datos_validos() {
        clientePrueba = new Customer(
            UUID.randomUUID(),
            "Juan Pérez",
            "juan.perez@email.com",
            "+573001234567",
            "Carrera 10 #20-30, Bogotá",
            CustomerStatus.ACTIVE
        );
    }

    @Given("un producto disponible en inventario")
    public void un_producto_disponible_en_inventario() {
        productoPrueba = new Product(
            UUID.randomUUID(),
            "Laptop ThinkPad",
            "Computador portátil de alta gama",
            new BigDecimal("2500000"),
            50,
            "Electrónica",
            true
        );
    }

    @When("el cliente crea un pedido con el producto")
    public void el_cliente_crea_un_pedido_con_el_producto() {
        itemsPedido = new ArrayList<>();
        Order.OrderItem item = new Order.OrderItem(
            productoPrueba.getId(),
            productoPrueba.getName(),
            productoPrueba.getPrice(),
            2
        );
        itemsPedido.add(item);

        try {
            ordenCreada = orderService.createOrder(clientePrueba, itemsPedido);
        } catch (Exception e) {
            exceptionCapturada = e;
        }
    }

    @Then("el pedido debe ser creado exitosamente")
    public void el_pedido_debe_ser_creado_exitosamente() {
        Assertions.assertNotNull(ordenCreada);
        Assertions.assertNotNull(ordenCreada.getId());
        Assertions.assertEquals(OrderStatus.PENDING, ordenCreada.getStatus());
        Assertions.assertEquals(clientePrueba.getId(), ordenCreada.getCustomer().getId());
    }

    @And("el monto total debe ser correcto")
    public void el_monto_total_debe_ser_correcto() {
        BigDecimal expectedTotal = productoPrueba.getPrice().multiply(new BigDecimal(2));
        Assertions.assertEquals(0, expectedTotal.compareTo(ordenCreada.getTotalAmount()));
    }

    @When("el cliente intenta crear un pedido con cantidad mayor al stock")
    public void el_cliente_intenta_crear_un_pedido_con_cantidad_mayor_al_stock() {
        itemsPedido = new ArrayList<>();
        Order.OrderItem item = new Order.OrderItem(
            productoPrueba.getId(),
            productoPrueba.getName(),
            productoPrueba.getPrice(),
            100
        );
        itemsPedido.add(item);

        try {
            ordenCreada = orderService.createOrder(clientePrueba, itemsPedido);
        } catch (ProductOutOfStockException e) {
            exceptionCapturada = e;
        } catch (Exception e) {
            exceptionCapturada = e;
        }
    }

    @Then("el sistema debe lanzar una excepción de stock insuficiente")
    public void el_sistema_debe_lanzar_una_excepcion_de_stock_insuficiente() {
        Assertions.assertNotNull(exceptionCapturada);
        Assertions.assertTrue(exceptionCapturada instanceof ProductOutOfStockException);
    }

    @When("el cliente modifica el estado del pedido a CONFIRMADO")
    public void el_cliente_modifica_el_estado_del_pedido_a_confirmado() {
        try {
            orderService.updateOrderStatus(ordenCreada.getId(), OrderStatus.CONFIRMED);
            ordenCreada = orderService.getOrderById(ordenCreada.getId());
        } catch (Exception e) {
            exceptionCapturada = e;
        }
    }

    @Then("el pedido debe mostrar estado CONFIRMADO")
    public void el_pedido_debe_mostrar_estado_confirmado() {
        Assertions.assertEquals(OrderStatus.CONFIRMED, ordenCreada.getStatus());
    }

    @When("el cliente cancela el pedido")
    public void el_cliente_cancela_el_pedido() {
        try {
            orderService.cancelOrder(ordenCreada.getId());
            ordenCreada = orderService.getOrderById(ordenCreada.getId());
        } catch (Exception e) {
            exceptionCapturada = e;
        }
    }

    @Then("el pedido debe mostrar estado CANCELLED")
    public void el_pedido_debe_mostrar_estado_cancelled() {
        Assertions.assertEquals(OrderStatus.CANCELLED, ordenCreada.getStatus());
    }

    @When("se busca un pedido con ID inexistente")
    public void se_busca_un_pedido_con_id_inexistente() {
        UUID idInexistente = UUID.randomUUID();
        try {
            orderService.getOrderById(idInexistente);
        } catch (OrderNotFoundException e) {
            exceptionCapturada = e;
        } catch (Exception e) {
            exceptionCapturada = e;
        }
    }

    @Then("el sistema debe lanzar excepción de pedido no encontrado")
    public void el_sistema_debe_lanzar_excepcion_de_pedido_no_encontrado() {
        Assertions.assertNotNull(exceptionCapturada);
        Assertions.assertTrue(exceptionCapturada instanceof OrderNotFoundException);
    }
}