package com.uade.inventory.infrastructure.adapter.out.messaging;

import com.uade.inventory.domain.event.ProductCreatedEvent;
import com.uade.inventory.domain.port.out.EventPublisherPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!rabbitmq & !kafka")
public class NoOpEventPublisherAdapter implements EventPublisherPort {

    private static final Logger log = LoggerFactory.getLogger(NoOpEventPublisherAdapter.class);

    @Override
    public void publishProductCreated(ProductCreatedEvent event) {
        log.info("[NoOp] Evento ignorado (sin broker activo): ProductCreated [id={}, name={}]",
                event.getProductId(), event.getName());
    }
}
