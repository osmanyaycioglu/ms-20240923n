package org.turkcell.training.spring.order.integration.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.turkcell.training.spring.order.services.models.Meal;
import org.turkcell.training.spring.restaurant.models.MealDto;

import java.util.List;

@Mapper
public interface IMealMapper {
    IMealMapper MEAL_MAPPER = Mappers.getMapper(IMealMapper.class);

    Meal toMeal(MealDto meal);

    MealDto toMealDto(Meal meal);

    List<Meal> toMeals(List<MealDto> meals);

    List<MealDto> toMealDtos(List<Meal> meals);


}
