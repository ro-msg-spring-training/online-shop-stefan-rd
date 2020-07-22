package ro.msg.learning.shop.repository;

import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.model.ProductCategory;

@Repository
public interface ProductCategoryRepository extends BaseRepository<ProductCategory, Integer> {
}
