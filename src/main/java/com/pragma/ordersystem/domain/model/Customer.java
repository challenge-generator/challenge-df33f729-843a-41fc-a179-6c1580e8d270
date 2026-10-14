package com.pragma.ordersystem.domain.model;

import java.util.UUID;
import java.util.regex.Pattern;

public class Customer {
    private final UUID id;
    private final String name;
    private final String email;
    private final String phone;
    private final String address;
    private final CustomerStatus status;

    public Customer(UUID id, String name, String email, String phone, String address, CustomerStatus status) {
        this.id = id != null ? id : UUID.randomUUID();
        this.name = validateName(name);
        this.email = validateEmail(email);
        this.phone = validatePhone(phone);
        this.address = validateAddress(address);
        this.status = status != null ? status : CustomerStatus.ACTIVE;
    }

    private String validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío");
        }
        return name;
    }

    private String validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("El email no puede ser nulo o vacío");
        }
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        if (!Pattern.matches(regex, email)) {
            throw new IllegalArgumentException("El email no tiene un formato válido");
        }
        return email;
    }

    private String validatePhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            throw new IllegalArgumentException("El teléfono no puede ser nulo o vacío");
        }
        String regex = "^[0-9+() -]+$";
        if (!Pattern.matches(regex, phone)) {
            throw new IllegalArgumentException("El teléfono no tiene un formato válido");
        }
        return phone;
    }

    private String validateAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("La dirección no puede ser nula o vacía");
        }
        return address;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public CustomerStatus getStatus() {
        return status;
    }

    public enum CustomerStatus {
        ACTIVE,
        INACTIVE,
        SUSPENDED
    }
}