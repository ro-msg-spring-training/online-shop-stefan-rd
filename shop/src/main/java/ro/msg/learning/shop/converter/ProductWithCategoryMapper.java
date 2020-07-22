package ro.msg.learning.shop.converter;

import ro.msg.learning.shop.dto.ProductWithCategoryDto;
import ro.msg.learning.shop.model.Product;

public class ProductWithCategoryMapper
{
    public static ProductWithCategoryDto modelToDto(Product product)
    {
        ProductWithCategoryDto dto =  ProductWithCategoryDto.builder()
                .name(product.getName())
                .description((product.getDescription()))
                .price(product.getPrice().toString())
                .weight(product.getWeight().toString())
                .imageUrl(product.getImageUrl())
                .categoryName((product.getProductCategory().getName()))
                .categoryDescription(product.getProductCategory().getDescription())
                .build();

        dto.setId(product.getId());
        return dto;
    }
}
