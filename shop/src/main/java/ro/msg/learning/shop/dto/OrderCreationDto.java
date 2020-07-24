package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderCreationDto implements Serializable
{
    private String createdAt;
    private AddressDto address;
    private ProductIdAndQuantityDto[] products;
}
