package org.turkcell.training.spring.order.input;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.turkcell.training.spring.order.input.models.OrderDto;
import org.turkcell.training.spring.order.input.models.OrderResponse;
import reactor.core.publisher.Mono;

@RequestMapping("/api/v1/order/process")
public interface IOrderProcessRestController {

    @PostMapping("/place")
    @Operation(summary = "Order yaratmak için",
            description = "uzun açılamalar falan filan")
    OrderResponse placeOrder(@Valid @RequestBody OrderDto orderDtoParam);

}
