package ro.msg.learning.shop.strategy;

import ro.msg.learning.shop.dto.OrderProduct;
import ro.msg.learning.shop.model.Stock;

import java.util.List;

public interface ILocationStrategy {
    List<Stock> findLocation(List<OrderProduct> products);
}
