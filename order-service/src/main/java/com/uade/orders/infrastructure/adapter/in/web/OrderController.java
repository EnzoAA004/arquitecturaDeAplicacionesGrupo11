package com.uade.orders.infrastructure.adapter.in.web;

import com.uade.orders.domain.model.Order;
import com.uade.orders.domain.port.in.OrderUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderUseCase orderUseCase;

    public OrderController(OrderUseCase orderUseCase) {
        this.orderUseCase = orderUseCase;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody CreateOrderRequest request,
                                             @AuthenticationPrincipal Jwt jwt) {
        Order createdOrder = orderUseCase.createOrder(request.getProductId(), request.getQuantity(), jwt.getSubject());
        return ResponseEntity.ok(createdOrder);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders(@AuthenticationPrincipal Jwt jwt) {
        return ResponseEntity.ok(orderUseCase.getAllOrders(jwt.getSubject(), isAdmin(jwt)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id, @AuthenticationPrincipal Jwt jwt) {
        return orderUseCase.getOrderById(id, jwt.getSubject(), isAdmin(jwt))
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    private boolean isAdmin(Jwt jwt) {
        Object claim = jwt.getClaims().get("roles");
        if (!(claim instanceof Collection<?> roles)) {
            return false;
        }

        return roles.stream()
            .filter(String.class::isInstance)
            .map(String.class::cast)
            .anyMatch(role -> "ROLE_ADMIN".equals(role) || "ADMIN".equals(role));
    }
}

