package org.turkcell.training.spring.order.services.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.turkcell.training.spring.order.input.models.MealDto;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Order {
    private String name;
    private String surname;
    private String        phoneNumber;
    private List<MealDto> meals;
    private LocalDateTime scheduleTime;
    private Long customerId;
    private EOrderStatus orderStatus;
    private LocalDateTime orderTime;
    private LocalDateTime completionTime;


}
