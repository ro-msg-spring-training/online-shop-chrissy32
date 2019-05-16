package ro.msg.learning.shop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.repository.ILocationRepository;
import ro.msg.learning.shop.repository.IStockRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class StocksService {
    private IStockRepository stockRepository;
    private ILocationRepository locationRepository;

    public List<Integer> exportStock(Integer locationID) {
        List<Integer> exportedStocks = new ArrayList<>();

        List<Stock> stocks = stockRepository.findByLocation(locationRepository.findById(locationID).get());
        stocks.forEach(stock -> exportedStocks.add(stock.getID()));

        return exportedStocks;
    }

}
