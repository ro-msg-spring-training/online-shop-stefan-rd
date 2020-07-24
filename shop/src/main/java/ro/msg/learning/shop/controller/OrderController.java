package ro.msg.learning.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ro.msg.learning.shop.dto.OrderCreationDto;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.exception.ShopException;
import ro.msg.learning.shop.service.OrderService;

@RestController
public class OrderController
{
    @Autowired
    private OrderService orderService;

    @PostMapping("/orders")
    public OrderDto createOrder(@RequestBody OrderCreationDto newOrderDto)
    {
        try {
            return this.orderService.createOrder(newOrderDto);
        }
        catch(ShopException exception)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
        }
    }
}
