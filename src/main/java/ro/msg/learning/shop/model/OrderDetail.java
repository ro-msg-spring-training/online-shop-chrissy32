package ro.msg.learning.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail extends BaseEntity<Integer> {
//    @EmbeddedId
//    private BaseIdentity<Integer, String> ID;
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Order.class)
    @JoinColumn(name = "orderID", referencedColumnName = "ID")
    private Integer order;
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Product.class)
    @JoinColumn(name = "product", referencedColumnName = "name")
    private String product;
    @Column
    private Integer quantity;

//    public OrderDetail() {}
//
//    public OrderDetail(Order Order, Product Product, Integer Quantity) {
//        this.Order = Order;
//        this.Product = Product;
//        ID.setFirstID(Order.getID());
//        ID.setSecondID(Product.getName());
//        setID(ID);
//        this.Quantity = Quantity;
//    }

}
