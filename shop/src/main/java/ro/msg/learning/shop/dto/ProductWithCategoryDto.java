package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.model.Product;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductWithCategoryDto implements Serializable {
    private int id;
    private String name;
    private String description;
    private String price;
    private String weight;
    private String imageUrl;

    private int categoryId;
    private String categoryName;
    private String categoryDescription;

    public static ProductWithCategoryDto convertModelToDto(Product product) {
        return ProductWithCategoryDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description((product.getDescription()))
                .price(product.getPrice() != null ? product.getPrice().toString() : "-1")
                .weight(product.getWeight() != null ? product.getWeight().toString() : "-1")
                .imageUrl(product.getImageUrl())
                .categoryId(product.getProductCategory() != null ? product.getProductCategory().getId() : -1)
                .categoryName((product.getProductCategory() != null ? product.getProductCategory().getName() : ""))
                .categoryDescription(product.getProductCategory() != null ? product.getProductCategory().getDescription() : "")
                .build();

    }
}
