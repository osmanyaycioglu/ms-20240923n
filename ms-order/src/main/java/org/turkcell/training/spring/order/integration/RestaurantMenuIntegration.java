package org.turkcell.training.spring.order.integration;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
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
    private final EurekaClient eurekaClient;
    private final IRestaurantMenuFeignClient restaurantMenuFeignClient;
    private       long         index = 0;
    // RestClient restClient;

    public PriceInfo getPrice2(Order orderParam) {
        MealsDto   mealsDtoLoc = new MealsDto();
        List<Meal> mealsLoc    = orderParam.getMeals();
        mealsDtoLoc.setMeals(IMealMapper.MEAL_MAPPER.toMealDtos(orderParam.getMeals()));
        PriceInfoDto priceInfoDtoLoc = restTemplate.postForObject("http://RESTAURANT/api/v1/restaurant/menu/get/price",
                                                                  mealsDtoLoc,
                                                                  PriceInfoDto.class);

//        ResponseEntity<PriceInfoDto> entityLoc = restClient.post()
//                                                           .uri("http://RESTAURANT/api/v1/restaurant/menu/get/price")
//                                                           .contentType(MediaType.APPLICATION_JSON)
//                                                           .body(mealsDtoLoc)
//                                                           .retrieve()
//                                                           .toEntity(PriceInfoDto.class);

        return IPriceInfoMapper.PRICE_MAPPER.toPriceInfo(priceInfoDtoLoc);
    }


    public PriceInfo getPrice3(Order orderParam) {
        MealsDto   mealsDtoLoc = new MealsDto();
        List<Meal> mealsLoc    = orderParam.getMeals();
        mealsDtoLoc.setMeals(IMealMapper.MEAL_MAPPER.toMealDtos(orderParam.getMeals()));
        Application        restaurantLoc   = eurekaClient.getApplication("RESTAURANT");
        List<InstanceInfo> instancesLoc    = restaurantLoc.getInstances();
        int                callIndex       = (int) (index++ % instancesLoc.size());
        InstanceInfo       instanceInfoLoc = instancesLoc.get(callIndex);
        RestTemplate       restTemplateLoc = new RestTemplate();

        PriceInfoDto priceInfoDtoLoc = restTemplateLoc.postForObject("http://"
                                                                     + instanceInfoLoc.getIPAddr()
                                                                     + ":"
                                                                     + instanceInfoLoc.getPort()
                                                                     + "/api/v1/restaurant/menu/get/price",
                                                                     mealsDtoLoc,
                                                                     PriceInfoDto.class);

        return IPriceInfoMapper.PRICE_MAPPER.toPriceInfo(priceInfoDtoLoc);
    }

    public PriceInfo getPrice(Order orderParam) {
        MealsDto   mealsDtoLoc = new MealsDto();
        List<Meal> mealsLoc    = orderParam.getMeals();
        mealsDtoLoc.setMeals(IMealMapper.MEAL_MAPPER.toMealDtos(orderParam.getMeals()));
        PriceInfoDto priceLoc = restaurantMenuFeignClient.getPrice(mealsDtoLoc);
        return IPriceInfoMapper.PRICE_MAPPER.toPriceInfo(priceLoc);
    }

}
