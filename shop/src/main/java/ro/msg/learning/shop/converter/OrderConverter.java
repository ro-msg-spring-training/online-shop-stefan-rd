package ro.msg.learning.shop.converter;

import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.model.Order;

import java.time.format.DateTimeFormatter;

public class OrderConverter
{
    public static OrderDto convertOrderToDto(Order order)
    {
        return OrderDto.builder()
                .address(AddressConverter.convertAddressToDto(order.getAddress()))
                .createdAt(order.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                .customerId(order.getCustomer() != null ? order.getCustomer().getId() : -1)
                .locationId(order.getShippedFrom() != null ? order.getShippedFrom().getId() : -1)
                .id(order.getId())
                .build();
    }
}
