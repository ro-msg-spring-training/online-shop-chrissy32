package ro.msg.learning.shop.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class OrderDetail extends BaseEntity<BaseIdentity<Integer, String>> {
    @EmbeddedId
    private BaseIdentity<Integer, String> ID;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderID", referencedColumnName = "ID")
    private Order Order;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Product", referencedColumnName = "Name")
    private Product Product;
    @Column
    private Integer Quantity;

    public OrderDetail() {}

    public OrderDetail(Order Order, Product Product, Integer Quantity) {
        this.Order = Order;
        this.Product = Product;
        ID.setFirstID(Order.getID());
        ID.setSecondID(Product.getName());
        setID(ID);
        this.Quantity = Quantity;
    }

}
