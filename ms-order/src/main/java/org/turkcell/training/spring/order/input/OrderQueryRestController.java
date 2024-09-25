package org.turkcell.training.spring.order.input;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order/query")
public class OrderQueryRestController {

    @GetMapping("/find/one")
    public void findOrder(){
    }

}
