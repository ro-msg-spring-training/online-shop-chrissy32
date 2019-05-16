package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.Query;
import ro.msg.learning.shop.model.Customer;

import java.util.List;

public interface ICustomerRepository extends IShopRepository<Customer, Integer> {
    @Query("SELECT username, password FROM Customer")
    List<Object[]> findUsernameAndPasswords();
}
