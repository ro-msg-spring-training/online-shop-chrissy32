package ro.msg.learning.shop.Model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Orders")
@Data
public class Order extends BaseEntity<Integer> {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ShippedFrom")
    private Location ShippedFrom;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Customer")
    private Customer Customer;
    @Column
    private LocalDateTime CreatedAt;
    @Column
    private String AddressCountry;
    @Column
    private String AddressCity;
    @Column
    private String AddressCounty;
    @Column
    private String AddressStreet;
    @OneToMany(mappedBy = "Order")
    private List<OrderDetail> orderDetails = new ArrayList<>();

    public Order() {}

    public Order(Location ShippedFrom, Customer Customer, LocalDateTime CreatedAt, String AddressCountry, String AddressCity, String AddressCounty, String AddressStreet) {
        this.ShippedFrom = ShippedFrom;
        this.Customer = Customer;
        this.CreatedAt = CreatedAt;
        this.AddressCountry = AddressCountry;
        this.AddressCity = AddressCity;
        this.AddressCounty = AddressCounty;
        this.AddressStreet = AddressStreet;
    }

}
