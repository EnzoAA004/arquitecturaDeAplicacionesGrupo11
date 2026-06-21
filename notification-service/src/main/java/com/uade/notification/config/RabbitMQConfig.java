package com.uade.notification.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("rabbitmq")
public class RabbitMQConfig {

    public static final String EXCHANGE = "inventory.exchange";
    public static final String QUEUE = "product.created.queue";
    public static final String ROUTING_KEY = "product.created";
    public static final String ORDER_EXCHANGE = "order.exchange";
    public static final String ORDER_QUEUE = "order.created.queue";
    public static final String ORDER_ROUTING_KEY = "order.created";

    @Bean
    public TopicExchange inventoryExchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Queue productCreatedQueue() {
        return QueueBuilder.durable(QUEUE).build();
    }

    @Bean
    public Binding productCreatedBinding(Queue productCreatedQueue,
                                         @Qualifier("inventoryExchange") TopicExchange inventoryExchange) {
        return BindingBuilder.bind(productCreatedQueue).to(inventoryExchange).with(ROUTING_KEY);
    }

    @Bean
    public TopicExchange orderExchange() {
        return new TopicExchange(ORDER_EXCHANGE);
    }

    @Bean
    public Queue orderCreatedQueue() {
        return QueueBuilder.durable(ORDER_QUEUE).build();
    }

    @Bean
    public Binding orderCreatedBinding(Queue orderCreatedQueue,
                                       @Qualifier("orderExchange") TopicExchange orderExchange) {
        return BindingBuilder.bind(orderCreatedQueue).to(orderExchange).with(ORDER_ROUTING_KEY);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
