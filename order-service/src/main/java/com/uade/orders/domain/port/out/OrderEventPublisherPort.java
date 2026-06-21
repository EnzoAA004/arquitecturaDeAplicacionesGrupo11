package com.uade.orders.domain.port.out;

import com.uade.orders.domain.event.OrderCreatedEvent;

public interface OrderEventPublisherPort {

    void publishOrderCreated(OrderCreatedEvent event);
}

