package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.Query;
import ro.msg.learning.shop.model.Supplier;

import java.util.List;

public interface ISupplierRepository extends IShopRepository<Supplier, Integer> {

    @Query("SELECT ID, name FROM Supplier")
    List<Object[]> findNames();
}
