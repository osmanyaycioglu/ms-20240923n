package org.turkcell.training.spring.order.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.turkcell.training.spring.order.integration.RestaurantMenuIntegration;
import org.turkcell.training.spring.order.integration.models.PriceInfo;
import org.turkcell.training.spring.order.services.models.Order;
import org.turkcell.training.spring.order.services.models.OrderResult;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
public class OrderProcessService {
    private final RestaurantMenuIntegration restaurantMenuIntegration;

    public OrderResult placeOrder(Order orderParam) {
        PriceInfo priceLoc = restaurantMenuIntegration.getPrice(orderParam);

        orderParam.setOrderId(UUID.randomUUID()
                                  .toString());
        // fill and persist to DB
        return OrderResult.builder()
                          .withOrderIdParam(orderParam.getOrderId())
                          .withDescParam(priceLoc.getDesc())
                          .withEstimatedDeliveryParam(LocalDateTime.now()
                                                                   .plusHours(1))
                          .build();
    }

}
