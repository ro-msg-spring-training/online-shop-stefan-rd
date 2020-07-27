package ro.msg.learning.shop.repository;

import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.Stock;

import java.util.Optional;

@Repository
public interface StockRepository extends BaseRepository<Stock, Integer>{
    Optional<Stock> findByProductAndLocation(Product product, Location location);
}
