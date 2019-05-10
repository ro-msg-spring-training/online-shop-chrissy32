package ro.msg.learning.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dto.OrderDTO;
import ro.msg.learning.shop.model.Order;
import ro.msg.learning.shop.service.OrdersService;

import java.time.LocalDateTime;

@RestController
class OrdersController {
    @Autowired
    private OrdersService ordersService;

    @PostMapping("/orders/{customerID}")
    private Order createOrder(@PathVariable Integer customerID, @RequestBody OrderDTO newOrder) throws Exception {
        newOrder.setTimestamp(LocalDateTime.now().withNano(0));
        return ordersService.createOrder(customerID, newOrder);
    }

}
