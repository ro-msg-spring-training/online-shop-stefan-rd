package ro.msg.learning.shop.service;

import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.dto.ProductWithCategoryDto;
import ro.msg.learning.shop.exception.ShopException;

import java.util.List;

public interface ProductService
{
    List<ProductWithCategoryDto> getAllProducts();

    ProductWithCategoryDto getProduct(int productId) throws ShopException;

    ProductDto saveProduct(ProductDto newProduct) throws ShopException;

    ProductDto updateProduct(int productId, ProductDto product) throws ShopException;

    void deleteProduct(int productId);


}
