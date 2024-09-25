package org.turkcell.training.spring.order.input.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDto {
    @NotBlank
    @Size(min = 2,max = 15)
    private String name;
    @NotBlank
    @Size(min = 3,max = 20)
    private String surname;
    @Size(min = 10,max = 11)
    private String phoneNumber;
    @Valid
    @Size(min = 1)
    private List<MealDto> meals;
    @Future
    private LocalDateTime scheduleTime;
}
