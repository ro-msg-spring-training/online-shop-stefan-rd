package ro.msg.learning.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.converter.ProductWithCategoryMapper;
import ro.msg.learning.shop.dto.ProductWithCategoryDto;
import ro.msg.learning.shop.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductController
{
    @Autowired
    private ProductService productService;

    @GetMapping(value = "/products")
    public List<ProductWithCategoryDto> getProducts() {
        return this.productService.getAllProducts().stream()
                .map(ProductWithCategoryMapper::convertModelToDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/products/{id}")
    public ProductWithCategoryDto getProduct(@PathVariable int id) {
        return ProductWithCategoryMapper.convertModelToDto(this.productService.getProduct(id));
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
    }

    @PostMapping("/products")
    public ProductWithCategoryDto saveProduct(@RequestBody ProductWithCategoryDto newProductWithCategory)
    {
        return ProductWithCategoryMapper
                .convertModelToDto(this.productService.saveProduct(
                        ProductWithCategoryMapper
                                .convertDtoToProductModel(newProductWithCategory),
                        newProductWithCategory.getCategoryId()));
    }

    @PutMapping("/products/{id}")
    public ProductWithCategoryDto updateProduct(@PathVariable int id, @RequestBody ProductWithCategoryDto updatedProductDto)
    {
        return ProductWithCategoryMapper
                .convertModelToDto(this.productService.updateProduct(id,
                        ProductWithCategoryMapper
                                .convertDtoToProductModel(updatedProductDto)));
    }
}
