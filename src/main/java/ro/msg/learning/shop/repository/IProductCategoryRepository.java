package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.Query;
import ro.msg.learning.shop.model.ProductCategory;

import java.util.List;


public interface IProductCategoryRepository extends IShopRepository<ProductCategory, Integer> {

    @Query("SELECT ID, name, description FROM ProductCategory")
    List<Object[]> findNameAndDescriptions();
}
