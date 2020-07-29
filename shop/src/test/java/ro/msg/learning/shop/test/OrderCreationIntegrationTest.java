package ro.msg.learning.shop.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import ro.msg.learning.shop.dto.AddressDto;
import ro.msg.learning.shop.dto.OrderCreationDto;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.dto.ProductIdAndQuantityDto;
import ro.msg.learning.shop.exception.ShopException;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.repository.OrderDetailRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.StockRepository;
import ro.msg.learning.shop.service.OrderService;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest
@Profile("test")
class OrderCreationIntegrationTest
{
    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private DataSource dataSource;

    private int productId1;
    private int productId2;


    @BeforeEach
    public void setUp()
    {
        Product product1 = this.productRepository.save(new Product());
        Product product2 = this.productRepository.save(new Product());
        this.productId1 = product1.getId();
        this.productId2 = product2.getId();

        Location location1 = new Location();

        Stock stock1 = Stock.builder().product(product1).location(location1).quantity(10).build();
        Stock stock2 = Stock.builder().product(product2).location(location1).quantity(10).build();
        Set<Stock> stocksLocation1 = new HashSet<>();
        stocksLocation1.add(stock1);
        stocksLocation1.add(stock2);
        location1.setStocks(stocksLocation1);
        this.locationRepository.save(location1);
    }

    @AfterEach
    public void tearDown()
    {
        this.stockRepository.deleteAll();
        this.productRepository.deleteAll();
        this.locationRepository.deleteAll();
    }

    @Test
    void createOrder_sufficientStock_orderAndOrderDetailsCreated()
    {
        ProductIdAndQuantityDto[] products = {new ProductIdAndQuantityDto(productId1, 2), new ProductIdAndQuantityDto(productId2, 3)};
        try{
            OrderDto order = this.orderService.createOrder(OrderCreationDto.builder()
                    .createdAt("2020-10-10 11:11")
                    .address(new AddressDto())
                    .products(products)
                    .build());
            Assert.assertEquals( 1, order.getId());
            Assert.assertEquals( 2, this.orderDetailRepository.findAll().size());
        }
        catch(ShopException shopException)
        {
            Assert.fail();
        }
    }

    @Test
    void createOrder_insufficientStock_exceptionThrown()
    {
        ProductIdAndQuantityDto[] products = {new ProductIdAndQuantityDto(1, 15), new ProductIdAndQuantityDto(2, 3)};
        Assert.assertThrows(ShopException.class, ()->this.orderService.createOrder(OrderCreationDto.builder()
                .createdAt("2020-10-10 11:11")
                .address(new AddressDto())
                .products(products)
                .build()));
    }
}
