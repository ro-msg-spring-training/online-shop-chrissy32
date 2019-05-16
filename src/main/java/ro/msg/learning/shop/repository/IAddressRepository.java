package ro.msg.learning.shop.repository;

import ro.msg.learning.shop.model.Address;

public interface IAddressRepository extends IShopRepository<Address, Integer> {
    Address findByAddressCountryAndAddressCityAndAddressCountyAndAddressStreet(String addressCountry, String addressCity, String addressCounty, String addressStreet);
}