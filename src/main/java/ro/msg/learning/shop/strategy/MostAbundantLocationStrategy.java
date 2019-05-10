package ro.msg.learning.shop.strategy;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import ro.msg.learning.shop.dto.OrderProduct;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.repository.IStockRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class MostAbundantLocationStrategy implements ILocationStrategy {
    @Autowired
    private IStockRepository stockRepository;

    @Override
    public List<Stock> findLocation(List<OrderProduct> products) {
        List<Stock> stocks = stockRepository.findAll();

        List<Stock> mostAbundantStocks = new ArrayList<>();

        Comparator<Stock> comparator = Comparator.comparing(Stock::getQuantity);
        stocks.sort(comparator.reversed());

        products.forEach(product -> {
            for(Stock stock : stocks){
                if (stock.getProduct().getID().equals(product.getID()) && stock.getQuantity() >= product.getQuantity()) {
                    mostAbundantStocks.add(stock);
                    break;
                }
            }
        });

        return mostAbundantStocks;
    }
}
