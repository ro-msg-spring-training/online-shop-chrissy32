package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.Query;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.Stock;

import java.util.List;

public interface IStockRepository extends IShopRepository<Stock, Integer> {
    @Query(value = "SELECT S FROM Stock S WHERE S.product = ?1 AND S.quantity >= ?2 ORDER BY S.quantity DESC")
    List<Stock> findStocksByProductAndQuantity(Product product, Integer quantity);

    List<Stock> findByLocation(Location location);
}
