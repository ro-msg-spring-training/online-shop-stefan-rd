package ro.msg.learning.shop.repository;

import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.model.Order;

@Repository
public interface OrderRepository extends BaseRepository<Order, Integer> {
}
