package ro.msg.learning.shop.service.strategy;

import ro.msg.learning.shop.exception.ShopException;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.utils.ProductLocationQuantity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SingleLocationStrategy implements Strategy {
    @Override
    public List<ProductLocationQuantity> findSuitableLocation(Map<Integer, Integer> productIdsAndQuantity, List<Location> locations) throws ShopException {
        List<ProductLocationQuantity> results = new ArrayList<>();
        if (locations.isEmpty()) {
            throw new ShopException("findSuitableLocation: Empty list of locations!");
        }
        for (Location location : locations) {
            Map<Product, Integer> productWithStock = location.getStocks().stream()
                    .collect(Collectors.toMap(Stock::getProduct, Stock::getQuantity));
            for (Map.Entry<Product, Integer> entry : productWithStock.entrySet()) {
                if (productIdsAndQuantity.containsKey(entry.getKey().getId())) {
                    if (productIdsAndQuantity.get(entry.getKey().getId()) <= entry.getValue()) {
                        ProductLocationQuantity result = ProductLocationQuantity.builder()
                                .productId(entry.getKey().getId())
                                .quantity(productIdsAndQuantity.get(entry.getKey().getId()))
                                .locationId(location.getId())
                                .build();
                        results.add(result);
                    } else {
                        results.clear();
                        break;
                    }
                }
            }
            if (results.size() == productIdsAndQuantity.size()) {
                return results;
            }
        }
        throw new ShopException("findSuitableLocation: Could not find a suitable location due to insufficient stock!");
    }
}
