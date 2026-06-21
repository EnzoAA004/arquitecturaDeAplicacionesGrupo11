package com.uade.orders.domain.event;

import java.time.Instant;

public class OrderCreatedEvent {

    private Long orderId;
    private Long productId;
    private Integer quantity;
    private String status;
    private Instant timestamp;

    public OrderCreatedEvent() {
    }

    public OrderCreatedEvent(Long orderId, Long productId, Integer quantity, String status) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.status = status;
        this.timestamp = Instant.now();
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}

