package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.model.OrderDetail;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderDetailDto {
    private ProductDto product;
    private int quantity;

    public static OrderDetailDto convertOrderDetailToDto(OrderDetail orderDetail) {
        return OrderDetailDto.builder()
                .product(ProductDto.convertProductToDto(orderDetail.getProduct()))
                .quantity(orderDetail.getQuantity())
                .build();
    }
}
