package com.uade.orders.infrastructure.adapter.in.messaging;

import com.uade.orders.domain.event.ProductCreatedEvent;
import com.uade.orders.infrastructure.adapter.out.messaging.RabbitMQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("rabbitmq")
public class ProductCreatedListener {

    private static final Logger log = LoggerFactory.getLogger(ProductCreatedListener.class);

    @RabbitListener(queues = RabbitMQConfig.PRODUCT_CREATED_QUEUE)
    public void handleProductCreated(ProductCreatedEvent event) {
        log.info("Evento consumido en order-service: product.created [productId={}, name={}]",
            event.getProductId(), event.getName());
    }
}

