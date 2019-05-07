package ro.msg.learning.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Stock extends BaseEntity<Integer> {
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Product.class)
    @JoinColumn(name = "product", referencedColumnName = "name")
    private String product;
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Location.class)
    @JoinColumn(name = "location", referencedColumnName = "name")
    private String location;
    @Column
    private Integer quantity;

//    public Stock() {}
//
//    public Stock(Product Product, Location Location, Integer Quantity) {
//        this.Product = Product;
//        this.Location = Location;
//        ID.setFirstID(Product.getName());
//        ID.setSecondID(Location.getName());
//        setID(ID);
//        this.Quantity = Quantity;
//    }
}
