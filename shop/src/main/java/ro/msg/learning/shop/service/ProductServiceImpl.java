package ro.msg.learning.shop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.converter.ProductMapper;
import ro.msg.learning.shop.converter.ProductWithCategoryMapper;
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

@Service
public class ProductServiceImpl implements ProductService
{
    public static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public List<ProductWithCategoryDto> getAllProducts() {
        log.info("-----getAllProducts -- method entered");
        List<ProductWithCategoryDto> products = productRepository.findAll().stream()
                .map(ProductWithCategoryMapper::convertModelToDto)
                .collect(Collectors.toList());
        log.info("-----getAllProducts -- method finished, products = {}", products);
        return products;
    }

    @Override
    public ProductWithCategoryDto getProduct(int productId) throws ShopException {
        log.info("-----getProduct -- method entered, productId = {}", productId);
        ProductWithCategoryDto product= ProductWithCategoryMapper.convertModelToDto(productRepository.findById(productId).orElseThrow(() -> new ShopException("productServiceImpl.getProduct: Invalid product id= " + productId + "!")));
        log.info("-----getProduct -- method finished, product = {}", product);
        return product;
    }

    @Override
    @Transactional
    public ProductDto saveProduct(ProductDto productDto) throws ShopException {
        log.info("-----saveProduct -- method entered, newProduct = {}", productDto);

        Optional<ProductCategory> optionalProductCategory = this.productCategoryRepository.findById(productDto.getCategoryId());
        ProductCategory productCategory = optionalProductCategory.orElseThrow(() -> new ShopException("productServiceImpl.saveProduct: Invalid category id= " + productDto.getCategoryId() + "!"));

        Product newProduct = ProductMapper.convertDtoToProduct(productDto);

        newProduct.setProductCategory(productCategory);
        productCategory.addProduct(newProduct);

        ProductDto savedProduct = ProductMapper.convertProductToDto(productRepository.save(newProduct));

        log.info("-----saveProduct -- method finished, savedProduct = {}", savedProduct);
        return savedProduct;
    }

    @Override
    @Transactional
    public ProductDto updateProduct(int productId, ProductDto productDto) throws ShopException {
        log.info("-----updateProduct -- method entered, productId = {}, product = {}", productId, productDto);

        Product updatedProduct = productRepository.findById(productId).orElseThrow(() -> new ShopException("productServiceImpl.updateProduct: Invalid product id= " + productId + "!"));

        Product product = ProductMapper.convertDtoToProduct(productDto);

        updatedProduct.setName(product.getName());
        updatedProduct.setDescription(product.getDescription());
        updatedProduct.setWeight(product.getWeight());
        updatedProduct.setPrice(product.getPrice());
        updatedProduct.setImageUrl(product.getImageUrl());

        ProductDto updatedProductDto = ProductMapper.convertProductToDto(updatedProduct);

        log.info("-----updateProduct -- method finished, updatedProduct = {}", updatedProductDto);
        return updatedProductDto;
    }

    @Override
    @Transactional
    public void deleteProduct(int productId) {
        log.info("-----deleteProduct -- method entered, productId = {}", productId);
        Optional<Product> optionalProduct = this.productRepository.findById(productId);
        if(optionalProduct.isPresent())
        {
            Product product = optionalProduct.get();
            ProductCategory productCategory = product.getProductCategory();
            productCategory.removeProduct(product);
            this.productRepository.deleteById(productId);
        }
        log.info("-----deleteProduct -- method finished");
    }
}
