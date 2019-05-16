package ro.msg.learning.shop.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dto.OrderDTO;
import ro.msg.learning.shop.model.Order;
import ro.msg.learning.shop.service.OrdersService;

import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
class OrdersController {
    private OrdersService ordersService;

    @PostMapping("/orders")
    private Order createOrder(@RequestBody OrderDTO newOrder) {
        newOrder.setTimestamp(LocalDateTime.now().withNano(0));
        return ordersService.createOrder(newOrder);
    }

}
