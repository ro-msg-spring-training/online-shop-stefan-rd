package ro.msg.learning.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ro.msg.learning.shop.dto.OrderCreationDto;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.exception.ShopException;
import ro.msg.learning.shop.service.OrderService;

@RestController
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/orders")
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderCreationDto newOrderDto) {
        try {
            return new ResponseEntity<>(this.orderService.createOrder(newOrderDto), HttpStatus.OK);
        } catch (ShopException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
        }
    }
}
