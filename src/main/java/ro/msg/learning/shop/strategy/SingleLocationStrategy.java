package ro.msg.learning.shop.strategy;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.dto.ProductQuantityDTO;
import ro.msg.learning.shop.exceptions.MissingStockException;
import ro.msg.learning.shop.exceptions.UnknownProductException;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.repository.IProductRepository;
import ro.msg.learning.shop.repository.IStockRepository;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
public class SingleLocationStrategy implements ILocationStrategy {
    private IStockRepository stockRepository;
    private IProductRepository productRepository;

    @Override
    public List<Stock> findLocation(List<ProductQuantityDTO> products) {
        Map<Integer, List<Stock>> locations = new HashMap<>();

        products.forEach(product -> {
            try{
                List<Stock> stocks = stockRepository.findStocksByProductAndQuantity(productRepository.findById(product.getProductID()).get(), product.getQuantity());

                if (stocks.isEmpty())
                    throw new MissingStockException();

                stocks.forEach(stock -> {
                    Integer locationID = stock.getLocation().getID();
                    List<Stock> lst = locations.get(locationID);
                    if (lst == null)
                        lst = new ArrayList<>();

                    lst.add(stock);
                    locations.put(locationID, lst);
                });

            } catch (NoSuchElementException e) {
                throw new UnknownProductException();
            }
        });

        List<Stock> singleLocationStocks = null;

        for (Map.Entry<Integer, List<Stock>> entry : locations.entrySet()) {
            if (entry.getValue().size() == products.size()) {
                singleLocationStocks = new ArrayList<>(entry.getValue());
                break;
            }
        }

        return singleLocationStocks;
    }
}
