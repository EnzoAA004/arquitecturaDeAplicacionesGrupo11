package com.uade.orders.infrastructure.adapter.out.messaging;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("rabbitmq")
public class RabbitMQConfig {

    public static final String INVENTORY_EXCHANGE = "inventory.exchange";
    public static final String PRODUCT_CREATED_QUEUE = "order.product.created.queue";
    public static final String PRODUCT_CREATED_ROUTING_KEY = "product.created";

    public static final String ORDER_EXCHANGE = "order.exchange";
    public static final String ORDER_CREATED_ROUTING_KEY = "order.created";

    @Bean
    public TopicExchange inventoryExchange() {
        return new TopicExchange(INVENTORY_EXCHANGE);
    }

    @Bean
    public Queue productCreatedQueue() {
        return QueueBuilder.durable(PRODUCT_CREATED_QUEUE).build();
    }

    @Bean
    public Binding productCreatedBinding(Queue productCreatedQueue,
                                         @Qualifier("inventoryExchange") TopicExchange inventoryExchange) {
        return BindingBuilder.bind(productCreatedQueue).to(inventoryExchange).with(PRODUCT_CREATED_ROUTING_KEY);
    }

    @Bean
    public TopicExchange orderExchange() {
        return new TopicExchange(ORDER_EXCHANGE);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}

