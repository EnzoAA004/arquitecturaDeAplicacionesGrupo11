package com.uade.orders.application.service;

import com.uade.orders.domain.exception.OrderAccessDeniedException;
import com.uade.orders.domain.event.OrderCreatedEvent;
import com.uade.orders.domain.model.Order;
import com.uade.orders.domain.port.in.OrderUseCase;
import com.uade.orders.domain.port.out.OrderEventPublisherPort;
import com.uade.orders.domain.port.out.OrderRepositoryPort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements OrderUseCase {

    private final OrderRepositoryPort orderRepositoryPort;
    private final OrderEventPublisherPort orderEventPublisherPort;

    public OrderService(OrderRepositoryPort orderRepositoryPort, OrderEventPublisherPort orderEventPublisherPort) {
        this.orderRepositoryPort = orderRepositoryPort;
        this.orderEventPublisherPort = orderEventPublisherPort;
    }

    @Override
    public Order createOrder(Long productId, Integer quantity, String username) {
        Order order = new Order();
        order.setProductId(productId);
        order.setQuantity(quantity);
        order.setStatus("CREATED");
        order.setCreatedAt(Instant.now());
        order.setUsername(username);

        Order saved = orderRepositoryPort.save(order);
        orderEventPublisherPort.publishOrderCreated(
            new OrderCreatedEvent(saved.getId(), saved.getProductId(), saved.getQuantity(), saved.getStatus())
        );
        return saved;
    }

    @Override
    public List<Order> getAllOrders(String username, boolean admin) {
        if (admin) {
            return orderRepositoryPort.findAll();
        }
        return orderRepositoryPort.findAllByUsername(username);
    }

    @Override
    public Optional<Order> getOrderById(Long id, String username, boolean admin) {
        if (admin) {
            return orderRepositoryPort.findById(id);
        }

        Optional<Order> order = orderRepositoryPort.findById(id);
        if (order.isPresent() && !username.equals(order.get().getUsername())) {
            throw new OrderAccessDeniedException("La orden solicitada no esta disponible para este usuario.");
        }

        return order;
    }
}

