package org.turkcell.training.spring.order.input.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderResponse {
    private String orderId;
    private LocalDateTime estimation;
    private String desc;
}
