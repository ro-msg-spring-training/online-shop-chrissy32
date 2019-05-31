package ro.msg.learning.shop.configuration;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.exceptions.InvalidStrategyException;
import ro.msg.learning.shop.repository.ILocationRepository;
import ro.msg.learning.shop.repository.IProductRepository;
import ro.msg.learning.shop.repository.IStockRepository;
import ro.msg.learning.shop.strategy.CloseProximityStrategy;
import ro.msg.learning.shop.strategy.ILocationStrategy;
import ro.msg.learning.shop.strategy.MostAbundantLocationStrategy;
import ro.msg.learning.shop.strategy.SingleLocationStrategy;


@Configuration
@AllArgsConstructor
public class LocationConfiguration {
    private final IStockRepository stockRepository;
    private final IProductRepository productRepository;
    private final ILocationRepository locationRepository;
    private enum Strategy {
        SINGLE, ABUNDANT, CLOSE
    }

    @Bean
    public ILocationStrategy locationStrategy(@Value("${location.strategy}") Strategy strategy, @Value("${api.key}") String apiKey) {
        switch (strategy) {
            case SINGLE:
                return new SingleLocationStrategy(stockRepository, productRepository);
            case ABUNDANT:
                return new MostAbundantLocationStrategy(stockRepository, productRepository);
            case CLOSE:
                return new CloseProximityStrategy(locationRepository, stockRepository, productRepository, apiKey);
            default:
                throw new InvalidStrategyException();
        }
    }
}
