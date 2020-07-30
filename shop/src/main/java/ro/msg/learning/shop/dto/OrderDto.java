package ro.msg.learning.shop.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.model.Order;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderDto implements Serializable {
    private int id;
    private int locationId;
    private int customerId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private String createdAt;

    private AddressDto address;

    private OrderDetailDto[] orderDetails;

    public static OrderDto convertOrderToDto(Order order) {
        OrderDto dto = OrderDto.builder()
                .address(AddressDto.convertAddressToDto(order.getAddress()))
                .createdAt(order.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                .customerId(order.getCustomer() != null ? order.getCustomer().getId() : -1)
                .locationId(order.getShippedFrom() != null ? order.getShippedFrom().getId() : -1)
                .id(order.getId())
                .build();

        List<OrderDetailDto> detailDtosList = order.getOrderDetails().stream()
                .map(OrderDetailDto::convertOrderDetailToDto)
                .collect(Collectors.toList());

        OrderDetailDto[] detailDtos = detailDtosList.toArray(new OrderDetailDto[0]);

        dto.setOrderDetails(detailDtos);
        return dto;
    }
}
