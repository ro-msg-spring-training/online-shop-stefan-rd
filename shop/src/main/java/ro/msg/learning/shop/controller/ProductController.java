package ro.msg.learning.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.dto.ProductWithCategoryDto;
import ro.msg.learning.shop.exception.ShopException;
import ro.msg.learning.shop.service.ProductService;

import java.util.List;

@RestController
public class ProductController
{
    @Autowired
    private ProductService productService;

    @GetMapping(value = "/products")
    public List<ProductWithCategoryDto> getProducts() {
        return this.productService.getAllProducts();
    }

    @GetMapping(value = "/products/{id}")
    public ProductWithCategoryDto getProduct(@PathVariable int id)
    {
        try {
            return this.productService.getProduct(id);
        }
        catch(ShopException exception)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
        }
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
    }

    @PostMapping("/products")
    public ProductDto saveProduct(@RequestBody ProductDto newProductDto){
        try {
            return this.productService.saveProduct(newProductDto);
        }
        catch(ShopException exception)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
        }
    }

    @PutMapping("/products/{id}")
    public ProductDto updateProduct(@PathVariable int id, @RequestBody ProductDto updatedProductDto){
        try {
            return this.productService.updateProduct(id, updatedProductDto);
        }
        catch(ShopException exception)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
        }
    }
}
