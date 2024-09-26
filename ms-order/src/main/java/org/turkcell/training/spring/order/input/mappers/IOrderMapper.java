package org.turkcell.training.spring.order.input.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.turkcell.training.spring.order.input.models.MealDto;
import org.turkcell.training.spring.order.input.models.OrderDto;
import org.turkcell.training.spring.order.input.models.OrderResponse;
import org.turkcell.training.spring.order.services.models.Meal;
import org.turkcell.training.spring.order.services.models.Order;
import org.turkcell.training.spring.order.services.models.OrderResult;

@Mapper
public interface IOrderMapper {
    IOrderMapper ORDER_MAPPER = Mappers.getMapper(IOrderMapper.class);

    Order toOrder(OrderDto order);

    OrderDto toOrderDto(Order order);

    Meal toMeal(MealDto meal);

    MealDto toMealDto(Meal meal);

    @Mapping(target = "estimation",source = "estimatedDelivery")
    OrderResponse toOrderResponse(OrderResult orderResultParam);


}
