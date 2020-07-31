package ro.msg.learning.shop.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.dto.ProductWithCategoryDto;
import ro.msg.learning.shop.exception.ShopException;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.ProductCategory;
import ro.msg.learning.shop.repository.ProductCategoryRepository;
import ro.msg.learning.shop.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductCategoryRepository productCategoryRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductWithCategoryDto> getAllProducts() {
        log.info("-----getAllProducts -- method entered");
        List<ProductWithCategoryDto> products = productRepository.findAll().stream()
                .map(ProductWithCategoryDto::convertModelToDto)
                .collect(Collectors.toList());
        log.info("-----getAllProducts -- method finished, products = {}", products);
        return products;
    }

    @Override
    public ProductWithCategoryDto getProduct(int productId) throws ShopException {
        log.info("-----getProduct -- method entered, productId = {}", productId);
        ProductWithCategoryDto product = ProductWithCategoryDto.convertModelToDto(productRepository.findById(productId).orElseThrow(() -> new ShopException("productServiceImpl.getProduct: Invalid product id= " + productId + "!")));
        log.info("-----getProduct -- method finished, product = {}", product);
        return product;
    }

    @Override
    @Transactional
    public ProductDto saveProduct(ProductDto productDto) throws ShopException {
        log.info("-----saveProduct -- method entered, newProduct = {}", productDto);

        Optional<ProductCategory> optionalProductCategory = this.productCategoryRepository.findById(productDto.getCategoryId());
        ProductCategory productCategory = optionalProductCategory.orElseThrow(() -> new ShopException("productServiceImpl.saveProduct: Invalid category id= " + productDto.getCategoryId() + "!"));

        Product newProduct = ProductDto.convertDtoToProduct(productDto);

        newProduct.setProductCategory(productCategory);
        productCategory.addProduct(newProduct);

        ProductDto savedProduct = ProductDto.convertProductToDto(productRepository.save(newProduct));

        log.info("-----saveProduct -- method finished, savedProduct = {}", savedProduct);
        return savedProduct;
    }

    @Override
    @Transactional
    public ProductDto updateProduct(int productId, ProductDto productDto) throws ShopException {
        log.info("-----updateProduct -- method entered, productId = {}, product = {}", productId, productDto);

        Product updatedProduct = productRepository.findById(productId).orElseThrow(() -> new ShopException("productServiceImpl.updateProduct: Invalid product id= " + productId + "!"));

        Product product = ProductDto.convertDtoToProduct(productDto);

        updatedProduct.setName(product.getName());
        updatedProduct.setDescription(product.getDescription());
        updatedProduct.setWeight(product.getWeight());
        updatedProduct.setPrice(product.getPrice());
        updatedProduct.setImageUrl(product.getImageUrl());

        ProductDto updatedProductDto = ProductDto.convertProductToDto(updatedProduct);

        log.info("-----updateProduct -- method finished, updatedProduct = {}", updatedProductDto);
        return updatedProductDto;
    }

    @Override
    @Transactional
    public void deleteProduct(int productId) {
        log.info("-----deleteProduct -- method entered, productId = {}", productId);
        Optional<Product> optionalProduct = this.productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            ProductCategory productCategory = product.getProductCategory();
            productCategory.removeProduct(product);
            this.productRepository.deleteById(productId);
        }
        log.info("-----deleteProduct -- method finished");
    }
}
