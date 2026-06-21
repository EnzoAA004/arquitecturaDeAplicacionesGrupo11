package com.uade.orders.infrastructure.adapter.out.messaging;

import com.uade.orders.domain.event.OrderCreatedEvent;
import com.uade.orders.domain.port.out.OrderEventPublisherPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("rabbitmq")
public class RabbitMQOrderEventPublisher implements OrderEventPublisherPort {

    private static final Logger log = LoggerFactory.getLogger(RabbitMQOrderEventPublisher.class);

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQOrderEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void publishOrderCreated(OrderCreatedEvent event) {
        rabbitTemplate.convertAndSend(
            RabbitMQConfig.ORDER_EXCHANGE,
            RabbitMQConfig.ORDER_CREATED_ROUTING_KEY,
            event
        );
        log.info("Evento publicado: order.created [orderId={}, productId={}]", event.getOrderId(), event.getProductId());
    }
}

