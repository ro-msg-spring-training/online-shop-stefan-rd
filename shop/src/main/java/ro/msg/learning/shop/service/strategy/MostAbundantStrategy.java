package ro.msg.learning.shop.service.strategy;

import ro.msg.learning.shop.exception.ShopException;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.utils.ProductLocationQuantity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MostAbundantStrategy implements Strategy
{
    @Override
    public List<ProductLocationQuantity> findSuitableLocation(Map<Integer, Integer> productIdsAndQuantity, List<Location> locations) throws ShopException {
        if(locations.isEmpty())
        {
            throw new ShopException("findSuitableLocation: Empty list of locations!");
        }
        Map<Integer, ProductLocationQuantity> results = new HashMap<>();
        for(Map.Entry<Integer, Integer> productIdAndQuantity: productIdsAndQuantity.entrySet())
        {
            results.put(productIdAndQuantity.getKey(), new ProductLocationQuantity(productIdAndQuantity.getKey(), -1, 0));
        }
        for(Location location : locations)
        {
            for(Stock stock : location.getStocks())
            {
                if(results.containsKey(stock.getProduct().getId()) && stock.getQuantity() > results.get(stock.getProduct().getId()).getQuantity())
                {
                    results.get(stock.getProduct().getId()).setLocationId(location.getId());
                    results.get(stock.getProduct().getId()).setQuantity(stock.getQuantity());
                }
            }
        }
        for(Map.Entry<Integer, Integer> productIdAndQuantity: productIdsAndQuantity.entrySet())
        {
            if(results.get(productIdAndQuantity.getKey()).getQuantity() < productIdAndQuantity.getValue())
            {
                throw new ShopException("findSuitableLocation: Could not find a suitable set of locations!");
            }
            else
            {
                results.get(productIdAndQuantity.getKey()).setQuantity(productIdAndQuantity.getValue());
            }
        }
        return new ArrayList<>(results.values());
    }
}
