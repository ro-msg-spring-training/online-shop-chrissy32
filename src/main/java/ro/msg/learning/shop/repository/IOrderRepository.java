package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.Query;
import ro.msg.learning.shop.model.Order;

import java.time.LocalDateTime;
import java.util.List;

public interface IOrderRepository extends IShopRepository<Order, Integer> {

    @Query("SELECT O FROM Order O WHERE createdAt BETWEEN :start AND :end")
    List<Order> findByCreatedAt(LocalDateTime start, LocalDateTime end);
}
