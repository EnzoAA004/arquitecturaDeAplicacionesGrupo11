package com.uade.orders.infrastructure.adapter.out.persistence;

import com.uade.orders.domain.model.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public Order toDomain(OrderJpaEntity entity) {
        return new Order(
            entity.getId(),
            entity.getProductId(),
            entity.getQuantity(),
            entity.getStatus(),
            entity.getCreatedAt(),
            entity.getUsername()
        );
    }

    public OrderJpaEntity toJpaEntity(Order order) {
        OrderJpaEntity entity = new OrderJpaEntity();
        entity.setId(order.getId());
        entity.setProductId(order.getProductId());
        entity.setQuantity(order.getQuantity());
        entity.setStatus(order.getStatus());
        entity.setCreatedAt(order.getCreatedAt());
        entity.setUsername(order.getUsername());
        return entity;
    }
}

