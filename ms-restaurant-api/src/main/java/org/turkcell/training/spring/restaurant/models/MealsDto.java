package org.turkcell.training.spring.restaurant.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MealsDto {
    private List<MealDto> meals;
}
