package org.turkcell.training.spring.order.integration;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.turkcell.training.spring.mscommon.error.ErrorObj;
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
    private final RestTemplate               restTemplate;
    private final EurekaClient               eurekaClient;
    private final IRestaurantMenuFeignClient restaurantMenuFeignClient;
    private       long                       index = 0;
    // RestClient restClient;

    @Retry(name = "restaurant-menu-get-price")
    public PriceInfo getPrice2(Order orderParam) {
        MealsDto   mealsDtoLoc = new MealsDto();
        List<Meal> mealsLoc    = orderParam.getMeals();
        mealsDtoLoc.setMeals(IMealMapper.MEAL_MAPPER.toMealDtos(orderParam.getMeals()));
        PriceInfoDto priceInfoDtoLoc = null;
        priceInfoDtoLoc = restTemplate.postForObject("http://RESTAURANT/api/v1/restaurant/menu/get/price",
                                                     mealsDtoLoc,
                                                     PriceInfoDto.class);

//        try {
//            priceInfoDtoLoc = restTemplate.postForObject("http://RESTAURANT/api/v1/restaurant/menu/get/price",
//                                                                      mealsDtoLoc,
//                                                                      PriceInfoDto.class);
//        } catch (RestClientResponseException eParam) {
//            ErrorObj responseBodyAsLoc = eParam.getResponseBodyAs(ErrorObj.class);
//            Integer  errorCodeLoc      = responseBodyAsLoc.getErrorCode();
//            switch (errorCodeLoc) {
//                case 1024:
//                    break;
//            }
//        }

//        ResponseEntity<PriceInfoDto> entityLoc = restClient.post()
//                                                           .uri("http://RESTAURANT/api/v1/restaurant/menu/get/price")
//                                                           .contentType(MediaType.APPLICATION_JSON)
//                                                           .body(mealsDtoLoc)
//                                                           .retrieve()
//                                                           .toEntity(PriceInfoDto.class);

        return IPriceInfoMapper.PRICE_MAPPER.toPriceInfo(priceInfoDtoLoc);
    }

    @Retry(name = "restaurant-menu-get-price2")
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

    @Retry(name = "restaurant-menu-get-price-feign", fallbackMethod = "getPriceFallback")
    public PriceInfo getPrice(Order orderParam) {
        MealsDto   mealsDtoLoc = new MealsDto();
        List<Meal> mealsLoc    = orderParam.getMeals();
        mealsDtoLoc.setMeals(IMealMapper.MEAL_MAPPER.toMealDtos(orderParam.getMeals()));
        PriceInfoDto priceLoc = restaurantMenuFeignClient.getPrice(mealsDtoLoc);
        return IPriceInfoMapper.PRICE_MAPPER.toPriceInfo(priceLoc);
    }

    public PriceInfo getPriceFallback(Order orderParam,
                                      Throwable throwableParam) {
        return new PriceInfo();
    }

}
