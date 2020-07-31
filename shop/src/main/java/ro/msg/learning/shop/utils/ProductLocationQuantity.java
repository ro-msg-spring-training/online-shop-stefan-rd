package ro.msg.learning.shop.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductLocationQuantity {
    private int productId;
    private int locationId;
    private int quantity;
}
