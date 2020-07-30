package ro.msg.learning.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.dto.ProductWithCategoryDto;
import ro.msg.learning.shop.exception.ShopException;
import ro.msg.learning.shop.service.ProductService;

import java.util.List;

@RestController
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/products")
    public ResponseEntity<List<ProductWithCategoryDto>> getProducts() {
        return new ResponseEntity<>(this.productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping(value = "/products/{id}")
    public ResponseEntity<ProductWithCategoryDto> getProduct(@PathVariable int id) {
        try {
            return new ResponseEntity<>(this.productService.getProduct(id), HttpStatus.OK);
        } catch (ShopException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
        }
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
    }

    @PostMapping("/products")
    public ResponseEntity<ProductDto> saveProduct(@RequestBody ProductDto newProductDto) {
        try {
            return new ResponseEntity<>(this.productService.saveProduct(newProductDto), HttpStatus.OK);
        } catch (ShopException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
        }
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable int id, @RequestBody ProductDto updatedProductDto) {
        try {
            return new ResponseEntity<>(this.productService.updateProduct(id, updatedProductDto), HttpStatus.OK);
        } catch (ShopException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
        }
    }
}
