package org.turkcell.training.spring.restaurant.inputs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.turkcell.training.spring.restaurant.IRestaurantMenuRestController;
import org.turkcell.training.spring.restaurant.models.MealsDto;
import org.turkcell.training.spring.restaurant.models.PriceInfoDto;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.Random;

@RestController
public class RestaurantMenuRestController implements IRestaurantMenuRestController {

    @Value("${server.port}")
    private Integer port;


    public PriceInfoDto getPrice(@RequestBody MealsDto mealsParam) {
        Random randomLoc = new SecureRandom();
        int    price     = randomLoc.nextInt(1_000);
        return new PriceInfoDto(new BigDecimal(price),
                                30F,
                                "response from : " + port);
    }
}
