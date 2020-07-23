package ro.msg.learning.shop.service;

import ro.msg.learning.shop.model.Product;

import java.util.List;

public interface ProductService
{
    List<Product> getAllProducts();

    Product getProduct(int productId);

    Product saveProduct(Product newProduct, int categoryId);

    Product updateProduct(int productId, Product product);

    void deleteProduct(int productId);


}
