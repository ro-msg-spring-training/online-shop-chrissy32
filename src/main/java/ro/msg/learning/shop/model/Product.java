package ro.msg.learning.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity<Integer> {
    @Column(nullable=false)
    private String name;
    @Column
    private String description;
    @Column(nullable=false)
    private BigDecimal price;
    @Column
    private Double weight;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = ProductCategory.class)
    @JoinColumn(name = "category", referencedColumnName = "ID")
    @JsonIgnoreProperties(value = {"products", "hibernateLazyInitializer"})
    private ProductCategory category;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Supplier.class)
    @JoinColumn(name = "supplier", referencedColumnName = "ID")
    @JsonIgnoreProperties(value = "products")
    private Supplier supplier;

    @Column
    private String imageURL;

    @OneToMany(mappedBy = "product", targetEntity = Stock.class, cascade=CascadeType.ALL)
    @JsonIgnoreProperties(value = {"product", "location", "quantity"})
    private List<Stock> stocks = new ArrayList<>();

    @OneToMany(mappedBy = "product", targetEntity = OrderDetail.class, cascade=CascadeType.ALL)
    @JsonIgnoreProperties(value = {"order", "product", "quantity"})
    private List<OrderDetail> orderDetails = new ArrayList<>();

    public Product(String name, String description, BigDecimal price, Double weight, ProductCategory category, Supplier supplier, String imageURL) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.weight = weight;
        this.category = category;
        this.supplier = supplier;
        this.imageURL = imageURL;
    }
}
