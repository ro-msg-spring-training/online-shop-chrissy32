package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.Product;

@AllArgsConstructor
@Data
public class StockDTO {
    private Location location;
    private Product product;
    private Integer quantity;
}
