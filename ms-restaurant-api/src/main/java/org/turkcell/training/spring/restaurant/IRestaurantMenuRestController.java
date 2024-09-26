package org.turkcell.training.spring.restaurant;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.turkcell.training.spring.restaurant.models.MealsDto;
import org.turkcell.training.spring.restaurant.models.PriceInfoDto;


@RequestMapping("/api/v1/restaurant/menu")
public interface IRestaurantMenuRestController {

    @PostMapping("/get/price")
    PriceInfoDto getPrice(@RequestBody MealsDto mealsParam);
}
