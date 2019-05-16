package ro.msg.learning.shop.strategy;

import ro.msg.learning.shop.dto.ProductQuantityDTO;
import ro.msg.learning.shop.model.Stock;

import java.util.List;

public interface ILocationStrategy {
    List<Stock> findLocation(List<ProductQuantityDTO> products);
}
