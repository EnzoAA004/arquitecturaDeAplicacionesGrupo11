package com.uade.notification.listener;

import com.uade.notification.config.RabbitMQConfig;
import com.uade.notification.event.OrderCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("rabbitmq")
public class OrderEventListener {

    private static final Logger log = LoggerFactory.getLogger(OrderEventListener.class);

    @RabbitListener(queues = RabbitMQConfig.ORDER_QUEUE)
    public void handleOrderCreated(OrderCreatedEvent event) {
        log.info("=== NOTIFICACION DE ORDEN RECIBIDA ===");
        log.info("Nueva orden creada:");
        log.info("  Order ID:  {}", event.getOrderId());
        log.info("  Product:   {}", event.getProductId());
        log.info("  Quantity:  {}", event.getQuantity());
        log.info("  Status:    {}", event.getStatus());
        log.info("  Timestamp: {}", event.getTimestamp());
        log.info("======================================");
    }
}

