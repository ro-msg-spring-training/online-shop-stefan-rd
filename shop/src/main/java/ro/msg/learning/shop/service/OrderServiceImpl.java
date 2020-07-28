package ro.msg.learning.shop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.converter.AddressConverter;
import ro.msg.learning.shop.converter.OrderConverter;
import ro.msg.learning.shop.dto.OrderCreationDto;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.dto.ProductIdAndQuantityDto;
import ro.msg.learning.shop.exception.ShopException;
import ro.msg.learning.shop.model.*;
import ro.msg.learning.shop.repository.*;
import ro.msg.learning.shop.service.strategy.Strategy;
import ro.msg.learning.shop.utils.ProductLocationQuantity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService
{
    public static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private Strategy strategy;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Transactional
    @Override
    public OrderDto createOrder(OrderCreationDto newOrder) throws ShopException {
        log.info("-----createOrder -- method entered, newOrder = {}", newOrder);

        //create dictionary with product id as key and quantity as value using the values inside the OrderCreationDto
        Map<Integer, Integer> productIdAndQuantity = new HashMap<>();
        for(ProductIdAndQuantityDto productIdAndQuantityDto : newOrder.getProducts())
        {
            productIdAndQuantity.put(productIdAndQuantityDto.getProductId(), productIdAndQuantityDto.getQuantity());
        }

        //get all existing product ids and check if the ones contained in the order are valid
        List<Integer> existingProducts = this.productRepository.findAll().stream()
                .map(BaseEntity::getId)
                .collect(Collectors.toList());
        for(Integer productId : productIdAndQuantity.keySet())
        {
            if(!existingProducts.contains(productId))
            {
                throw new ShopException("orderServiceImpl.createOrder: Unknown product ID(s)!");
            }
        }

        //apply the strategy
        List<Location> locations = locationRepository.findAll();
        List<ProductLocationQuantity> strategyResults = strategy.findSuitableLocation(productIdAndQuantity, locations);
        log.info("-----createOrder -- strategy strategyResults: {}", strategyResults);

        //update stock
        this.updateStocks(strategyResults);

        //create order and return it
        OrderDto orderDto = this.saveOrder(newOrder, strategyResults);
        log.info("-----createOrder -- method finished, orderDto = {}", orderDto);
        return orderDto;
    }

    @Transactional
    public void updateStocks(List<ProductLocationQuantity> strategyResults) throws ShopException {
        log.info("-----updateStocks -- method entered, strategyResults = {}", strategyResults);

        for(ProductLocationQuantity result : strategyResults)
        {
            Product product = this.productRepository.findById(result.getProductId()).orElseThrow(() -> new ShopException("orderServiceImpl.updateStocks: Invalid product id!"));
            Location location = this.locationRepository.findById(result.getLocationId()).orElseThrow(() -> new ShopException("orderServiceImpl.updateStocks: Invalid location id!"));
            Stock stock = this.stockRepository.findByProductAndLocation(product, location).orElseThrow(() -> new ShopException("orderServiceImpl.updateStocks: Stock does not exist!"));
            stock.setQuantity(stock.getQuantity() - result.getQuantity());
        }

        log.info("-----updateStocks -- method finished");
    }

    @Transactional
    public OrderDto saveOrder(OrderCreationDto newOrder, List<ProductLocationQuantity> strategyResults) throws ShopException {
        log.info("-----saveOrder -- method entered, newOrder = {}, strategyResults = {}", newOrder, strategyResults);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime createdAt = LocalDateTime.parse(newOrder.getCreatedAt(), formatter);
        Address address = AddressConverter.convertDtoToAddress(newOrder.getAddress());
        Order order = Order.builder()
                .address(address)
                .createdAt(createdAt)
                .build();
        order = this.orderRepository.save(order);

        for(ProductLocationQuantity result: strategyResults)
        {
            Product product = this.productRepository.findById(result.getProductId()).orElseThrow(() -> new ShopException("orderServiceImpl.updateStocks: Invalid product id!"));
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setCorrespondingOrder(order);
            orderDetail.setProduct(product);
            orderDetail.setQuantity(result.getQuantity());
            product.addDetail(orderDetail);
            order.addDetail(orderDetail);
            this.orderDetailRepository.save(orderDetail);
        }

        log.info("-----saveOrder -- method finished");
        return OrderConverter.convertOrderToDto(order);
    }
}
