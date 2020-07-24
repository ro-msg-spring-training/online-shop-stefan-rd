package ro.msg.learning.shop.service.strategy;

import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.utils.ProductLocationQuantity;

import java.util.List;

public class SingleLocationStrategy implements Strategy
{
    @Override
    public List<ProductLocationQuantity> findSuitableLocation(List<Integer> productIds, List<Stock> stocks) {
        return null;
    }
}
