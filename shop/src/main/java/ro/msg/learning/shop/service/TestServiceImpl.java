package ro.msg.learning.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.OrderDetail;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.repository.*;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("test")
public class TestServiceImpl implements TestService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public void populate()
    {
        Product product1 = this.productRepository.save(new Product());
        Product product2 = this.productRepository.save(new Product());

        Location location1 = new Location();

        Stock stock1 = Stock.builder().product(product1).location(location1).quantity(10).build();
        Stock stock2 = Stock.builder().product(product2).location(location1).quantity(10).build();
        Set<Stock> stocksLocation1 = new HashSet<>();
        stocksLocation1.add(stock1);
        stocksLocation1.add(stock2);
        location1.setStocks(stocksLocation1);
        this.locationRepository.save(location1);
    }

    @Override
    public void clear()
    {
        this.orderDetailRepository.deleteAll();
        this.orderRepository.deleteAll();
        this.stockRepository.deleteAll();
        this.productRepository.deleteAll();
        this.locationRepository.deleteAll();
    }
}
