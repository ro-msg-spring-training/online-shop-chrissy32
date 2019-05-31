package ro.msg.learning.shop.strategy;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dto.OrderDTO;
import ro.msg.learning.shop.dto.StockDTO;

import java.util.List;

@Component
public interface ILocationStrategy {
    List<StockDTO> findLocation(OrderDTO orderDTO);
}
