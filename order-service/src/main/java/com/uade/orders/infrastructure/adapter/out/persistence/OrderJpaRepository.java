package com.uade.orders.infrastructure.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderJpaRepository extends JpaRepository<OrderJpaEntity, Long> {
	List<OrderJpaEntity> findAllByUsername(String username);
}

