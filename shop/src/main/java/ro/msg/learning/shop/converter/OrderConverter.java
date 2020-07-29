package ro.msg.learning.shop.converter;

import ro.msg.learning.shop.dto.OrderDetailDto;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.model.Order;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class OrderConverter
{
    public static OrderDto convertOrderToDto(Order order)
    {
        OrderDto dto =  OrderDto.builder()
                .address(AddressConverter.convertAddressToDto(order.getAddress()))
                .createdAt(order.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                .customerId(order.getCustomer() != null ? order.getCustomer().getId() : -1)
                .locationId(order.getShippedFrom() != null ? order.getShippedFrom().getId() : -1)
                .id(order.getId())
                .build();

        List<OrderDetailDto> detailDtosList =  order.getOrderDetails().stream()
                .map(OrderDetailConverter::convertOrderDetailToDto)
                .collect(Collectors.toList());

        OrderDetailDto[] detailDtos = detailDtosList.toArray(new OrderDetailDto[0]);

        dto.setOrderDetails(detailDtos);
        return dto;
    }
}
