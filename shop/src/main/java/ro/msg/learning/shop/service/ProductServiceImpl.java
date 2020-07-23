package ro.msg.learning.shop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.ProductCategory;
import ro.msg.learning.shop.repository.ProductCategoryRepository;
import ro.msg.learning.shop.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService
{
    public static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public List<Product> getAllProducts() {
        log.info("-----getAllProducts -- method entered");
        List<Product> products = productRepository.findAll();
        log.info("-----getAllProducts -- method finished, products = {}", products);
        return products;
    }

    @Override
    public Product getProduct(int productId) {
        log.info("-----getProduct -- method entered, productId = {}", productId);
        Product product= productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Invalid product id!"));
        log.info("-----getProduct -- method finished, product = {}", product);
        return product;
    }

    @Override
    @Transactional
    public Product saveProduct(Product newProduct, int categoryId) {
        log.info("-----saveProduct -- method entered, newProduct = {}, categoryId = {}", newProduct, categoryId);
        Optional<ProductCategory> optionalProductCategory = this.productCategoryRepository.findById(categoryId);
        ProductCategory productCategory = optionalProductCategory.orElseThrow(() -> new RuntimeException("Invalid category id!"));
        newProduct.setProductCategory(productCategory);
        productCategory.addProduct(newProduct);
        Product savedProduct = productRepository.save(newProduct);
        log.info("-----saveProduct -- method finished, savedProduct = {}", savedProduct);
        return savedProduct;
    }

    @Override
    @Transactional
    public Product updateProduct(int productId, Product product) {
        log.info("-----updateProduct -- method entered, productId = {}, product = {}", productId, product);
        Product updatedProduct = productRepository.findById(productId).orElse(product);
        updatedProduct.setName(product.getName());
        updatedProduct.setDescription(product.getDescription());
        updatedProduct.setWeight(product.getWeight());
        updatedProduct.setPrice(product.getPrice());
        updatedProduct.setImageUrl(product.getImageUrl());
        log.info("-----updateProduct -- method finished, updatedProduct = {}", updatedProduct);
        return updatedProduct;
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
