package com.uade.orders.domain.port.out;

import com.uade.orders.domain.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepositoryPort {

    Order save(Order order);

    List<Order> findAll();

    List<Order> findAllByUsername(String username);

    Optional<Order> findById(Long id);
}

