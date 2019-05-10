package ro.msg.learning.shop.strategy;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import ro.msg.learning.shop.dto.OrderProduct;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.repository.IStockRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
public class SingleLocationStrategy implements ILocationStrategy {
    @Autowired
    private IStockRepository stockRepository;

    @Override
    public List<Stock> findLocation(List<OrderProduct> products) {
        List<Stock> stocks = stockRepository.findAll();
        Map<Integer, List<Stock>> locations = new HashMap<>();

        stocks.forEach(stock -> products.forEach(product -> {
            if (stock.getProduct().getID().equals(product.getID()) && stock.getQuantity() >= product.getQuantity()) {
                Integer locationID = stock.getLocation().getID();

                List<Stock> lst = locations.get(locationID);
                if (lst == null)
                    lst = new ArrayList<>();

                lst.add(stock);
                locations.put(locationID, lst);
            }
        }));

        int valid = products.size();
        List<Stock> singleLocationStocks = null;

        for (Map.Entry<Integer, List<Stock>> entry : locations.entrySet()) {
            if (entry.getValue().size() == valid) {
                singleLocationStocks = new ArrayList<>(entry.getValue());
                break;
            }
        }

        return singleLocationStocks;
    }
}
