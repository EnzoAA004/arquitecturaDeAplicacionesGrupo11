package com.uade.orders.domain.exception;

public class OrderAccessDeniedException extends RuntimeException {

    public OrderAccessDeniedException(String message) {
        super(message);
    }
}

