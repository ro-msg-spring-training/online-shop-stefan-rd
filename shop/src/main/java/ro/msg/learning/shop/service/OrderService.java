package ro.msg.learning.shop.service;


import ro.msg.learning.shop.dto.OrderCreationDto;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.exception.ShopException;

public interface OrderService {
    OrderDto createOrder(OrderCreationDto newOrder) throws ShopException;
}
