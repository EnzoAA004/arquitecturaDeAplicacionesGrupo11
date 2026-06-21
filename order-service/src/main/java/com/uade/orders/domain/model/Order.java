package com.uade.orders.domain.model;

import java.time.Instant;

public class Order {

    private Long id;
    private Long productId;
    private Integer quantity;
    private String status;
    private Instant createdAt;
    private String username;

    public Order() {
    }

    public Order(Long id, Long productId, Integer quantity, String status, Instant createdAt, String username) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.status = status;
        this.createdAt = createdAt;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

