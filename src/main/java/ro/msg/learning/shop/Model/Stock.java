package ro.msg.learning.shop.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Stock extends BaseEntity<BaseIdentity<String, String>> {
    @EmbeddedId
    private BaseIdentity<String, String> ID;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Product", referencedColumnName = "Name")
    private Product Product;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Location", referencedColumnName = "Name")
    private Location Location;
    @Column
    private Integer Quantity;

    public Stock() {}

    public Stock(Product Product, Location Location, Integer Quantity) {
        this.Product = Product;
        this.Location = Location;
        ID.setFirstID(Product.getName());
        ID.setSecondID(Location.getName());
        setID(ID);
        this.Quantity = Quantity;
    }
}
