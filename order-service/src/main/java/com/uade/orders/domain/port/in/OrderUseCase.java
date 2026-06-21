package com.uade.orders.domain.port.in;

import com.uade.orders.domain.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderUseCase {

    Order createOrder(Long productId, Integer quantity, String username);

    List<Order> getAllOrders(String username, boolean admin);

    Optional<Order> getOrderById(Long id, String username, boolean admin);
}

