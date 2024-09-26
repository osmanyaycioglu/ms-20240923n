package org.turkcell.training.spring.order.integration;


import org.springframework.cloud.openfeign.FeignClient;
import org.turkcell.training.spring.restaurant.IRestaurantMenuRestController;

@FeignClient(value = "RESTAURANT",contextId = "context1")
public interface IRestaurantMenuFeignClient extends IRestaurantMenuRestController {

//    @PostMapping("/api/v1/restaurant/menu/get/price")
//    PriceInfoDto getPrice(@RequestBody MealsDto mealsParam);
}
