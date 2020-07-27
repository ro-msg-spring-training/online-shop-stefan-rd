package ro.msg.learning.shop.service.strategy;

import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.utils.ProductLocationQuantity;

import java.util.List;
import java.util.Map;

public interface Strategy
{
    List<ProductLocationQuantity> findSuitableLocation(Map<Integer, Integer> productIdsAndQuantity, List<Location> locations);
}
