package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.model.Product;

import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductDto implements Serializable {
    private int id;
    private String name;
    private String description;
    private String price;
    private String weight;
    private String imageUrl;
    private int categoryId;
    private int supplierId;

    public static Product convertDtoToProduct(ProductDto dto) {
        return Product.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(new BigDecimal(dto.getPrice()))
                .weight(Double.valueOf(dto.getWeight()))
                .imageUrl(dto.getImageUrl())
                .build();
    }

    public static ProductDto convertProductToDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName() != null ? product.getName() : "")
                .description(product.getDescription() != null ? product.getDescription() : "")
                .price(product.getPrice() != null ? product.getPrice().toString() : "-1")
                .weight(product.getWeight() != null ? product.getWeight().toString() : "-1")
                .imageUrl(product.getImageUrl() != null ? product.getImageUrl() : "")
                .supplierId(product.getSupplier() != null ? product.getSupplier().getId() : -1)
                .categoryId(product.getProductCategory() != null ? product.getProductCategory().getId() : -1)
                .build();
    }
}
