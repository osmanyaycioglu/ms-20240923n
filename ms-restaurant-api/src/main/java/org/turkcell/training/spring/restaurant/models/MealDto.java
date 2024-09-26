package org.turkcell.training.spring.restaurant.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MealDto {
    private Long mealId;
    private String mealName;
    private Float portion;
}
