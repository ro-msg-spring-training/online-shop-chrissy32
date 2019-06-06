package ro.msg.learning.shop.repository;

import ro.msg.learning.shop.model.Order;
import ro.msg.learning.shop.model.OrderDetail;

import java.util.List;

public interface IOrderDetailRepository extends IShopRepository<OrderDetail, Integer> {

    List<OrderDetail> findByOrder(Order order);
}