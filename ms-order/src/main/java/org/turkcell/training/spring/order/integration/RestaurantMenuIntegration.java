package org.turkcell.training.spring.order.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.turkcell.training.spring.order.integration.mappers.IMealMapper;
import org.turkcell.training.spring.order.integration.mappers.IPriceInfoMapper;
import org.turkcell.training.spring.order.integration.models.PriceInfo;
import org.turkcell.training.spring.order.services.models.Meal;
import org.turkcell.training.spring.order.services.models.Order;
import org.turkcell.training.spring.restaurant.models.MealsDto;
import org.turkcell.training.spring.restaurant.models.PriceInfoDto;

import java.util.List;


@Service
@RequiredArgsConstructor
public class RestaurantMenuIntegration {
    private final RestTemplate restTemplate;

    public PriceInfo getPrice(Order orderParam) {
        MealsDto   mealsDtoLoc = new MealsDto();
        List<Meal> mealsLoc    = orderParam.getMeals();
        mealsDtoLoc.setMeals(IMealMapper.MEAL_MAPPER.toMealDtos(orderParam.getMeals()));
        PriceInfoDto priceInfoDtoLoc = restTemplate.postForObject("http://RESTAURANT/api/v1/restaurant/menu/get/price",
                                                                  mealsDtoLoc,
                                                                  PriceInfoDto.class);
        return IPriceInfoMapper.PRICE_MAPPER.toPriceInfo(priceInfoDtoLoc);
    }

}
