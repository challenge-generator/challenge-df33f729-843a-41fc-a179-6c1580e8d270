package com.pragma.ordersystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Clase principal de la aplicación Spring Boot para el sistema de gestión de pedidos.
 * <p>
 * Esta clase inicia la aplicación y configura el escaneo de componentes para las capas
 * dominio, aplicación e infraestructura según el patrón hexagonal.
 * </p>
 */
@SpringBootApplication
@ComponentScan(basePackages = {
    "com.pragma.ordersystem.domain",
    "com.pragma.ordersystem.application",
    "com.pragma.ordersystem.infrastructure",
    "com.pragma.ordersystem.config"
})
public class OrdersystemApplication {

    /**
     * Método principal que inicia la aplicación Spring Boot.
     *
     * @param args Argumentos de línea de comandos.
     */
    public static void main(String[] args) {
        SpringApplication.run(OrdersystemApplication.class, args);
    }
}