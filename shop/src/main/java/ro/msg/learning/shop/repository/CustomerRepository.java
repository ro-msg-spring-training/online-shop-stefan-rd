package ro.msg.learning.shop.repository;

import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.model.Customer;

import java.util.Optional;

@Repository
public interface CustomerRepository extends BaseRepository<Customer, Integer> {
    Optional<Customer> findByUsername(String username);
}
