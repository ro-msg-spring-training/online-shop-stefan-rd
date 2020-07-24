package ro.msg.learning.shop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dto.OrderCreationDto;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.exception.ShopException;
import ro.msg.learning.shop.service.strategy.Strategy;

import java.util.ArrayList;

@Service
public class OrderServiceImpl implements OrderService
{
    public static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private Strategy strategy;

    @Override
    public OrderDto createOrder(OrderCreationDto newOrder) throws ShopException {
        return null;
    }
}
