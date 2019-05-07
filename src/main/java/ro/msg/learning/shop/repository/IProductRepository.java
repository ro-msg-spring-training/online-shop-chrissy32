package ro.msg.learning.shop.repository;

import org.springframework.data.repository.CrudRepository;
import ro.msg.learning.shop.model.Product;

//public interface IProductRepository extends IShopRepository<Product, Integer> {
public interface IProductRepository extends CrudRepository<Product, Integer> {
}
