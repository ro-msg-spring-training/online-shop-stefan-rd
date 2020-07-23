package ro.msg.learning.shop.converter;

import ro.msg.learning.shop.dto.ProductWithCategoryDto;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.ProductCategory;

import java.math.BigDecimal;

public class ProductWithCategoryMapper
{
    public static ProductWithCategoryDto convertModelToDto(Product product)
    {
        return  ProductWithCategoryDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description((product.getDescription()))
                .price(product.getPrice() != null ? product.getPrice().toString() : "-1")
                .weight(product.getWeight() != null ? product.getWeight().toString() : "-1")
                .imageUrl(product.getImageUrl())
                .categoryId(product.getProductCategory().getId())
                .categoryName((product.getProductCategory().getName()))
                .categoryDescription(product.getProductCategory().getDescription())
                .build();

    }

    public static Product convertDtoToProductModel(ProductWithCategoryDto dto)
    {
        return  Product.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(new BigDecimal(dto.getPrice()))
                .weight(Double.valueOf(dto.getWeight()))
                .imageUrl(dto.getImageUrl())
                .build();
    }

    public static ProductCategory dtoToProductCategoryModel(ProductWithCategoryDto dto)
    {
        ProductCategory productCategory = ProductCategory.builder()
                .name(dto.getCategoryName())
                .description(dto.getCategoryDescription())
                .build();

        productCategory.setId(dto.getCategoryId());
        return productCategory;
    }


}
