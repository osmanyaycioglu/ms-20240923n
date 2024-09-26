package org.turkcell.training.spring.restaurant.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceInfoDto {
    private BigDecimal price;
    private Float discount;
    private String desc;
}

