package ro.msg.learning.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "Orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseEntity<Integer> {
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Location.class)
    @JoinColumn(name = "shippedFrom", referencedColumnName = "name")
    private String shippedFrom;
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Customer.class)
    @JoinColumn(name = "customer", referencedColumnName = "username")
    private String customer;
    @Column
    private LocalDateTime createdAt;
    @OneToOne(fetch = FetchType.LAZY, targetEntity = Address.class)
    @JoinColumn(name = "address", referencedColumnName = "ID")
    private Integer address;
    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails = new ArrayList<>();


}
