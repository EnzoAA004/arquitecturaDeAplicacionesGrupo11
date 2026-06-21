package com.uade.orders.infrastructure.adapter.in.web;

import com.uade.orders.domain.exception.OrderAccessDeniedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OrderAccessDeniedException.class)
    public ResponseEntity<Map<String, String>> handleOrderAccessDenied(OrderAccessDeniedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
            .body(Map.of("message", ex.getMessage()));
    }
}

