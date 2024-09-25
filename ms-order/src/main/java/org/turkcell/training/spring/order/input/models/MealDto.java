package org.turkcell.training.spring.order.input.models;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MealDto {
    @NotBlank
    private String mealName;
    @DecimalMin("1")
    private Double portion;

}
