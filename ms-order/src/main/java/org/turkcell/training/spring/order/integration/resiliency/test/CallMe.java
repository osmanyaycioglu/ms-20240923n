package org.turkcell.training.spring.order.integration.resiliency.test;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.turkcell.training.spring.order.integration.models.PriceInfo;
import org.turkcell.training.spring.order.services.models.Order;

import java.math.BigDecimal;

@Service
public class CallMe {
    private long counter = 0;

    @Retry(name = "restaurant-menu-get-price-feign")
    @CircuitBreaker(name = "restaurant-menu-get-price-cb")
    public PriceInfo xyz(Order orderParam) {
        if (counter < 20) {
            try {
                Thread.sleep(200);
            } catch (Exception exp) {
            }

            counter++;
            if (counter % 3 == 0){
                throw new IllegalArgumentException("ill " + counter);
            }

        }
        PriceInfo priceInfoLoc = new PriceInfo();
        priceInfoLoc.setPrice(BigDecimal.TEN);
        return priceInfoLoc;
    }

    public PriceInfo getPriceFallback(Order orderParam,
                                      Throwable throwableParam) {
        return new PriceInfo();
    }

}
