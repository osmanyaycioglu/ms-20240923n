package org.turkcell.training.spring.order.input;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.turkcell.training.spring.order.input.models.OrderDto;
import org.turkcell.training.spring.order.input.models.OrderResponse;

@RestController
public class OrderProcessRestController implements IOrderProcessRestController {

    public OrderResponse placeOrder(@Valid @RequestBody OrderDto orderDtoParam) {
        return null;
    }


}
