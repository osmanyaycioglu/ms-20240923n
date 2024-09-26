package org.turkcell.training.spring.order.services.models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderResult {

    private String desc;
    private String orderId;
    private LocalDateTime estimatedDelivery;

    @Builder(setterPrefix = "with")
    public OrderResult(final String descParam,
                       final String orderIdParam,
                       final LocalDateTime estimatedDeliveryParam) {
        desc              = descParam;
        orderId           = orderIdParam;
        estimatedDelivery = estimatedDeliveryParam;
    }
}
