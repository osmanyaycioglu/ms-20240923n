package org.turkcell.training.spring.order.integration.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceInfo {
    private BigDecimal price;
    private Float discount;
    private String desc;
}

