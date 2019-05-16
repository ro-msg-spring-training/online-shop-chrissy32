package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.Query;
import ro.msg.learning.shop.model.Address;

public interface IAddressRepository extends IShopRepository<Address, Integer> {
    @Query("SELECT A FROM Address A WHERE A.addressCountry = ?1 AND A.addressCity = ?2 " +
            "AND A.addressCounty = ?3 AND A.addressStreet = ?4")
    Address findIDByCountryAndCityAndCountyAndStreet(String addressCountry, String addressCity, String addressCounty, String addressStreet);

}