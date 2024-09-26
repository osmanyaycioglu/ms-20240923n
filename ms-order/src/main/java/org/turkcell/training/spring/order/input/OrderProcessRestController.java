package org.turkcell.training.spring.order.input;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.turkcell.training.spring.order.input.mappers.IOrderMapper;
import org.turkcell.training.spring.order.input.models.OrderDto;
import org.turkcell.training.spring.order.input.models.OrderResponse;
import org.turkcell.training.spring.order.services.OrderProcessService;
import org.turkcell.training.spring.order.services.models.OrderResult;

@RestController
@RequiredArgsConstructor
public class OrderProcessRestController implements IOrderProcessRestController {
    private final OrderProcessService orderProcessService;

    public OrderResponse placeOrder(@Valid @RequestBody OrderDto orderDtoParam) {
        return IOrderMapper.ORDER_MAPPER.toOrderResponse(orderProcessService.placeOrder(IOrderMapper.ORDER_MAPPER.toOrder(orderDtoParam)));
    }


}
