package org.turkcell.training.spring.order.integration.resiliency.test;

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
    public PriceInfo xyz(Order orderParam) {
        counter++;
        if (counter % 3 == 0){
            throw new IllegalArgumentException("ill " + counter);
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
