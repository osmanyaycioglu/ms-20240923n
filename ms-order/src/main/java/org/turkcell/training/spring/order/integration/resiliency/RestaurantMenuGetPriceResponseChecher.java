package org.turkcell.training.spring.order.integration.resiliency;

import org.turkcell.training.spring.order.integration.models.PriceInfo;

import java.util.function.Predicate;

public class RestaurantMenuGetPriceResponseChecher implements Predicate<PriceInfo> {

    @Override
    public boolean test(final PriceInfo priceInfo) {
        if (priceInfo.getPrice() == null) {
            return true;
        }
        return false;
    }
}
