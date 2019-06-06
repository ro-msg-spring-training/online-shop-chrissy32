package ro.msg.learning.shop.repository;

import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.Revenue;

import java.time.LocalDate;
import java.util.List;

public interface IRevenueRepository extends IShopRepository<Revenue, Integer> {

    Revenue findByLocationAndDate(Location location, LocalDate date);

    List<Revenue> findByDate(LocalDate date);

}
