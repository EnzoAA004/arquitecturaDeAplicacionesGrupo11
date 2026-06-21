package com.uade.orders.infrastructure.adapter.out.messaging;

import com.uade.orders.domain.event.OrderCreatedEvent;
import com.uade.orders.domain.port.out.OrderEventPublisherPort;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!rabbitmq")
public class NoOpOrderEventPublisher implements OrderEventPublisherPort {

    @Override
    public void publishOrderCreated(OrderCreatedEvent event) {
        // No-op para ejecuciones donde RabbitMQ no esta activo.
    }
}

