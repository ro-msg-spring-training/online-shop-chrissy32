package ro.msg.learning.shop.JPARepos;

import ro.msg.learning.shop.Model.BaseIdentity;
import ro.msg.learning.shop.Model.OrderDetail;

public interface IOrderDetailRepository extends IShopRepository<OrderDetail, BaseIdentity<Integer, String>> {
}