package ro.msg.learning.shop.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.strategy.ILocationStrategy;
import ro.msg.learning.shop.strategy.MostAbundantLocationStrategy;
import ro.msg.learning.shop.strategy.SingleLocationStrategy;


@Configuration
public class LocationConfiguration {
    @Value("${location.strategy}")
    private String strategy;

    @Bean
    ILocationStrategy iLocationStrategy() {
        if (strategy.equals("singleLocation"))
            return new SingleLocationStrategy();
        return new MostAbundantLocationStrategy();
    }
}
