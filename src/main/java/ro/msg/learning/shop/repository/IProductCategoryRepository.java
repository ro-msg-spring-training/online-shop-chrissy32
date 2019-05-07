package ro.msg.learning.shop.repository;

import org.springframework.data.repository.CrudRepository;
import ro.msg.learning.shop.model.ProductCategory;


public interface IProductCategoryRepository extends CrudRepository<ProductCategory, Integer>{//IShopRepository<ProductCategory, Integer> {
}
