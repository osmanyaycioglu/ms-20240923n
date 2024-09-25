package org.turkcell.training.spring.order.input;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.turkcell.training.spring.order.input.models.OrderDto;
import org.turkcell.training.spring.order.input.models.OrderResponse;

@RestController
@RequestMapping("/api/v1/order/process")
public class OrderProcessRestController {

    @PostMapping("/place")
    public OrderResponse placeOrder(@Valid @RequestBody OrderDto orderDtoParam) {
        return null;
    }


}
