package ro.msg.learning.shop.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Location extends BaseEntity<Integer> {
    @Column
    private String Name;
    @Column
    private String AddressCountry;
    @Column
    private String AddressCity;
    @Column
    private String AddressCounty;
    @Column
    private String AddressStreet;
    @OneToMany(mappedBy = "Location")
    private List<Stock> stocks = new ArrayList<>();
    @OneToMany(mappedBy = "ShippedFrom")
    private List<Order> orders = new ArrayList<>();
    @OneToMany(mappedBy = "Location")
    private List<Revenue> revenues = new ArrayList<>();

    public Location() {}

    public Location(String Name, String AddressCountry, String AddressCity, String AddressCounty, String AddressStreet) {
        this.Name = Name;
        this.AddressCountry = AddressCountry;
        this.AddressCity = AddressCity;
        this.AddressCounty = AddressCounty;
        this.AddressStreet = AddressStreet;
    }
}
