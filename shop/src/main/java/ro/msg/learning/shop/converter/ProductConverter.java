package ro.msg.learning.shop.converter;

import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.model.Product;

import java.math.BigDecimal;

public class ProductConverter
{
    public static Product convertDtoToProduct(ProductDto dto)
    {
        return  Product.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(new BigDecimal(dto.getPrice()))
                .weight(Double.valueOf(dto.getWeight()))
                .imageUrl(dto.getImageUrl())
                .build();
    }

    public static ProductDto convertProductToDto(Product product)
    {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice() != null ? product.getPrice().toString() : "-1")
                .weight(product.getWeight() != null ? product.getWeight().toString() : "-1")
                .imageUrl(product.getImageUrl())
                .supplierId(product.getSupplier().getId())
                .categoryId(product.getProductCategory().getId())
                .build();
    }


}
