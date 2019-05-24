package ro.msg.learning.shop.strategy;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.dto.ProductQuantityDTO;
import ro.msg.learning.shop.dto.StockDTO;
import ro.msg.learning.shop.exceptions.MissingStockException;
import ro.msg.learning.shop.exceptions.UnknownProductException;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.repository.IProductRepository;
import ro.msg.learning.shop.repository.IStockRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@AllArgsConstructor
@NoArgsConstructor
public class MostAbundantLocationStrategy implements ILocationStrategy {
    private IStockRepository stockRepository;
    private IProductRepository productRepository;

    @Override
    public List<StockDTO> findLocation(List<ProductQuantityDTO> products) {
        List<StockDTO> mostAbundantStocks = new ArrayList<>();

        products.forEach(product -> {
            try {
                List<Stock> stocks = stockRepository.findStocksByProductAndQuantity(productRepository.findById(product.getProductID()).get(), product.getQuantity());

                if (stocks.isEmpty()) {
                    throw new MissingStockException();
                }

                Stock stock = stocks.get(0);
                StockDTO stockDTO = new StockDTO(stock.getLocation(), stock.getProduct(), stock.getQuantity());

                mostAbundantStocks.add(stockDTO);

            } catch (NoSuchElementException e) {
                throw new UnknownProductException();
            }
        });

        return mostAbundantStocks;
    }
}
