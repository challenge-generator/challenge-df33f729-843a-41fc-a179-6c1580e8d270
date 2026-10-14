package com.pragma.ordersystem.infrastructure.entity;

import com.pragma.ordersystem.domain.model.Customer;
import com.pragma.ordersystem.domain.model.Customer.CustomerStatus;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "customers")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(length = 20)
    private String phone;

    @Column(length = 300)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private CustomerStatus status;

    public CustomerEntity() {
    }

    public CustomerEntity(UUID id, String name, String email, String phone, 
                          String address, CustomerStatus status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CustomerStatus getStatus() {
        return status;
    }

    public void setStatus(CustomerStatus status) {
        this.status = status;
    }

    public Customer toDomain() {
        return new Customer(
            this.id,
            this.name,
            this.email,
            this.phone,
            this.address,
            this.status
        );
    }

    public static CustomerEntity fromDomain(Customer customer) {
        if (customer == null) {
            return null;
        }
        return new CustomerEntity(
            customer.getId(),
            customer.getName(),
            customer.getEmail(),
            customer.getPhone(),
            customer.getAddress(),
            customer.getStatus()
        );
    }
}