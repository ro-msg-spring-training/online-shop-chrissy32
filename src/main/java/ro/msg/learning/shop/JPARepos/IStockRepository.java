package ro.msg.learning.shop.JPARepos;

import ro.msg.learning.shop.Model.BaseIdentity;
import ro.msg.learning.shop.Model.Stock;

public interface IStockRepository extends IShopRepository<Stock, BaseIdentity<String, String>> {
}
