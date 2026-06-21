package com.uade.orders.infrastructure.adapter.out.persistence;

import com.uade.orders.domain.model.Order;
import com.uade.orders.domain.port.out.OrderRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OrderPersistenceAdapter implements OrderRepositoryPort {

    private final OrderJpaRepository repository;
    private final OrderMapper mapper;

    public OrderPersistenceAdapter(OrderJpaRepository repository, OrderMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Order save(Order order) {
        return mapper.toDomain(repository.save(mapper.toJpaEntity(order)));
    }

    @Override
    public List<Order> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Order> findAllByUsername(String username) {
        return repository.findAllByUsername(username).stream().map(mapper::toDomain).toList();
    }

    @Override
    public Optional<Order> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }
}

