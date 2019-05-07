package ro.msg.learning.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location extends BaseEntity<Integer> {
    @Column
    private String name;
    @OneToOne(fetch = FetchType.LAZY, targetEntity = Address.class)
    @JoinColumn(name = "address", referencedColumnName = "ID")
    private Integer address;
    @OneToMany(mappedBy = "location")
    private List<Stock> stocks = new ArrayList<>();
    @OneToMany(mappedBy = "shippedFrom")
    private List<Order> orders = new ArrayList<>();
    @OneToMany(mappedBy = "location")
    private List<Revenue> revenues = new ArrayList<>();
}
