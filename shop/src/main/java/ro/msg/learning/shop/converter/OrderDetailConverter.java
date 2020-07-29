package ro.msg.learning.shop.converter;

import ro.msg.learning.shop.dto.OrderDetailDto;
import ro.msg.learning.shop.model.OrderDetail;

public class OrderDetailConverter
{
    public static OrderDetailDto convertOrderDetailToDto(OrderDetail orderDetail)
    {
        return OrderDetailDto.builder()
                .product(ProductConverter.convertProductToDto(orderDetail.getProduct()))
                .quantity(orderDetail.getQuantity())
                .build();
    }
}
