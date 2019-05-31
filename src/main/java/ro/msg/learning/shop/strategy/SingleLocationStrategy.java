package ro.msg.learning.shop.strategy;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.dto.OrderDTO;
import ro.msg.learning.shop.dto.StockDTO;
import ro.msg.learning.shop.exceptions.MissingStockException;
import ro.msg.learning.shop.exceptions.UnknownProductException;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.repository.IProductRepository;
import ro.msg.learning.shop.repository.IStockRepository;

import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
public class SingleLocationStrategy implements ILocationStrategy {
    private IStockRepository stockRepository;
    private IProductRepository productRepository;

    @Override
    public List<StockDTO> findLocation(OrderDTO orderDTO) {
        Map<Integer, List<StockDTO>> locations = new HashMap<>();

        orderDTO.getProducts().forEach(product -> {
            try{
                List<Stock> stocks = stockRepository.findStocksByProductAndQuantity(productRepository.findById(product.getProductID()).get(), product.getQuantity());

                if (stocks.isEmpty()) {
                    throw new MissingStockException();
                }

                List<StockDTO> stockDTOS = stocks.stream()
                        .map(stock -> new StockDTO(stock.getLocation(), stock.getProduct(), stock.getQuantity()))
                        .collect(Collectors.toList());

                stockDTOS.forEach(stockDTO -> {
                    Integer locationID = stockDTO.getLocation().getID();
                    List<StockDTO> lst = locations.get(locationID);
                    if (lst == null) {
                        lst = new ArrayList<>();
                    }

                    lst.add(stockDTO);
                    locations.put(locationID, lst);
                });

            } catch (NoSuchElementException e) {
                throw new UnknownProductException();
            }
        });

        List<StockDTO> singleLocationStocks = null;

        for (Map.Entry<Integer, List<StockDTO>> entry : locations.entrySet()) {
            if (entry.getValue().size() == orderDTO.getProducts().size()) {
                singleLocationStocks = new ArrayList<>(entry.getValue());
                break;
            }
        }

        return singleLocationStocks;
    }
}
