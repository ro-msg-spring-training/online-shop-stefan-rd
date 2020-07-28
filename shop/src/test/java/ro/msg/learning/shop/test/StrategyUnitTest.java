package ro.msg.learning.shop.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ro.msg.learning.shop.exception.ShopException;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.service.strategy.MostAbundantStrategy;
import ro.msg.learning.shop.service.strategy.SingleLocationStrategy;
import ro.msg.learning.shop.service.strategy.Strategy;
import ro.msg.learning.shop.utils.ProductLocationQuantity;

import java.util.*;

class StrategyUnitTest
{
    private final Strategy mostAbundant = new MostAbundantStrategy();
    private final Strategy singleLocation = new SingleLocationStrategy();
    private  List<Location> locations;
    private Map<Integer, Integer> productIdsAndQuantity;

    @BeforeEach
    public void setUp()
    {
        this.productIdsAndQuantity = new HashMap<>();
        productIdsAndQuantity.put(1, 2);
        productIdsAndQuantity.put(2, 4);
        productIdsAndQuantity.put(3, 3);

        this.locations = new ArrayList<>();
    }

    @AfterEach
    public void tearDown()
    {
        this.productIdsAndQuantity = null;
        this.locations = null;
    }

    @Test
    public void mostAbundant_notEnoughStock_exceptionThrown()
    {
        Product product1 = new Product();
        product1.setId(1);
        Product product2 = new Product();
        product2.setId(2);
        Product product3 = new Product();
        product3.setId(3);

        Location location1 = new Location();
        location1.setId(1);
        Location location2 = new Location();
        location2.setId(2);

        Stock stock1 = Stock.builder().product(product1).location(location1).quantity(10).build();
        Stock stock2 = Stock.builder().product(product2).location(location1).quantity(10).build();
        Set<Stock> stocksLocation1 = new HashSet<>();
        stocksLocation1.add(stock1);
        stocksLocation1.add(stock2);
        location1.setStocks(stocksLocation1);
        this.locations.add(location1);

        //stock for product with id 3 is completely missing
        Assert.assertThrows(ShopException.class, () -> this.mostAbundant.findSuitableLocation(productIdsAndQuantity, locations));

        Stock stock3 = Stock.builder().product(product3).location(location2).quantity(1).build();
        Set<Stock> stocksLocation2 = new HashSet<>();
        stocksLocation2.add(stock3);
        location2.setStocks(stocksLocation2);
        this.locations.add(location2);

        //stock is lower than the required one
        Assert.assertThrows(ShopException.class, () -> this.mostAbundant.findSuitableLocation(productIdsAndQuantity, locations));
    }

    @Test
    public void mostAbundant_emptyLocationList_exceptionThrown()
    {
        Assert.assertThrows(ShopException.class, () -> this.mostAbundant.findSuitableLocation(productIdsAndQuantity, locations));
    }

    @Test
    public void mostAbundant_suitableLocations_locationsFound() {
        Product product1 = new Product();
        product1.setId(1);
        Product product2 = new Product();
        product2.setId(2);
        Product product3 = new Product();
        product3.setId(3);

        Location location1 = new Location();
        location1.setId(1);
        Location location2 = new Location();
        location2.setId(2);

        Stock stock1 = Stock.builder().product(product1).location(location1).quantity(10).build();
        Stock stock2 = Stock.builder().product(product2).location(location1).quantity(10).build();
        Set<Stock> stocksLocation1 = new HashSet<>();
        stocksLocation1.add(stock1);
        stocksLocation1.add(stock2);
        location1.setStocks(stocksLocation1);
        this.locations.add(location1);

        Stock stock3 = Stock.builder().product(product3).location(location2).quantity(10).build();
        Stock stock4 = Stock.builder().product(product1).location(location2).quantity(20).build();
        Set<Stock> stocksLocation2 = new HashSet<>();
        stocksLocation2.add(stock3);
        stocksLocation2.add(stock4);
        location2.setStocks(stocksLocation2);
        this.locations.add(location2);

        List<ProductLocationQuantity> predictedResults = new ArrayList<>();
        ProductLocationQuantity result3 = new ProductLocationQuantity(3, 2, 3);
        ProductLocationQuantity result2 = new ProductLocationQuantity(2, 1, 4);
        ProductLocationQuantity result1 = new ProductLocationQuantity(1, 2, 2);
        predictedResults.add(result1);
        predictedResults.add(result2);
        predictedResults.add(result3);

        try {
            List<ProductLocationQuantity> actualResult = this.mostAbundant.findSuitableLocation(this.productIdsAndQuantity, this.locations);
            Assert.assertEquals(predictedResults, actualResult);
        }
        catch(ShopException exception)
        {
            Assert.fail();
        }
}


    @Test
    public void singleLocation_suitableLocation_locationFound()
    {
        Product product1 = new Product();
        product1.setId(1);
        Product product2 = new Product();
        product2.setId(2);
        Product product3 = new Product();
        product3.setId(3);

        Location location1 = new Location();
        location1.setId(1);
        Location location2 = new Location();
        location2.setId(2);
        Location location3 = new Location();
        location3.setId(3);

        Stock stock1 = Stock.builder().product(product1).location(location1).quantity(1).build();
        Stock stock2 = Stock.builder().product(product2).location(location1).quantity(1).build();
        Stock stock3 = Stock.builder().product(product3).location(location1).quantity(5).build();
        Set<Stock> stocksLocation1 = new HashSet<>();
        stocksLocation1.add(stock1);
        stocksLocation1.add(stock2);
        stocksLocation1.add(stock3);
        location1.setStocks(stocksLocation1);
        this.locations.add(location1);

        Stock stock4 = Stock.builder().product(product1).location(location2).quantity(4).build();
        Stock stock5 = Stock.builder().product(product2).location(location2).quantity(6).build();
        Stock stock6 = Stock.builder().product(product3).location(location2).quantity(3).build();
        Set<Stock> stocksLocation2 = new HashSet<>();
        stocksLocation2.add(stock4);
        stocksLocation2.add(stock5);
        stocksLocation2.add(stock6);
        location2.setStocks(stocksLocation2);
        this.locations.add(location2);

        Stock stock7 = Stock.builder().product(product1).location(location3).quantity(10).build();
        Stock stock8 = Stock.builder().product(product2).location(location3).quantity(10).build();
        Stock stock9 = Stock.builder().product(product3).location(location3).quantity(10).build();
        Set<Stock> stocksLocation3 = new HashSet<>();
        stocksLocation3.add(stock7);
        stocksLocation3.add(stock8);
        stocksLocation3.add(stock9);
        location3.setStocks(stocksLocation3);
        this.locations.add(location3);

        List<ProductLocationQuantity> predictedResults = new ArrayList<>();
        ProductLocationQuantity result1 = new ProductLocationQuantity(1, 2, 2);
        ProductLocationQuantity result2 = new ProductLocationQuantity(2, 2, 4);
        ProductLocationQuantity result3 = new ProductLocationQuantity(3, 2, 3);
        predictedResults.add(result1);
        predictedResults.add(result2);
        predictedResults.add(result3);

        try {
            List<ProductLocationQuantity> actualResult = this.singleLocation.findSuitableLocation(this.productIdsAndQuantity, this.locations);
            Assert.assertEquals(predictedResults, actualResult);
        }
        catch(ShopException exception)
        {
            Assert.fail();
        }
    }

    @Test
    public void singleLocation_notEnoughStock_exceptionThrown()
    {
        Product product1 = new Product();
        product1.setId(1);
        Product product2 = new Product();
        product2.setId(2);
        Product product3 = new Product();
        product3.setId(3);

        Location location1 = new Location();
        location1.setId(1);
        Location location2 = new Location();
        location2.setId(2);

        Stock stock1 = Stock.builder().product(product1).location(location1).quantity(1).build();
        Stock stock2 = Stock.builder().product(product2).location(location1).quantity(10).build();
        Stock stock3 = Stock.builder().product(product3).location(location1).quantity(1).build();
        Set<Stock> stocksLocation1 = new HashSet<>();
        stocksLocation1.add(stock1);
        stocksLocation1.add(stock2);
        stocksLocation1.add(stock3);
        location1.setStocks(stocksLocation1);
        this.locations.add(location1);

        Stock stock4 = Stock.builder().product(product1).location(location2).quantity(4).build();
        Stock stock5 = Stock.builder().product(product2).location(location2).quantity(6).build();
        Stock stock6 = Stock.builder().product(product3).location(location2).quantity(1).build();
        Set<Stock> stocksLocation2 = new HashSet<>();
        stocksLocation2.add(stock4);
        stocksLocation2.add(stock5);
        stocksLocation2.add(stock6);
        location2.setStocks(stocksLocation2);
        this.locations.add(location2);

        Assert.assertThrows(ShopException.class, () -> this.singleLocation.findSuitableLocation(productIdsAndQuantity, locations));
    }

    @Test
    public void singleLocation_emptyLocationList_exceptionThrown()
    {
        Assert.assertThrows(ShopException.class, () -> this.singleLocation.findSuitableLocation(productIdsAndQuantity, locations));
    }


}
