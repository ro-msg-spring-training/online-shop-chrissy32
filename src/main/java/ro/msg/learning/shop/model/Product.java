package ro.msg.learning.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private BigDecimal price;
    @Column
    private Double weight;
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = ProductCategory.class)
    @JoinColumn(name = "productcategory", referencedColumnName = "ID")
    private Integer category;
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Supplier.class)
    @JoinColumn(name = "supplier", referencedColumnName = "name")
    private String supplier;
    @Column
    private String imageURL;
    @OneToMany(mappedBy = "product")
    private List<Stock> stocks = new ArrayList<>();
    @OneToMany(mappedBy = "product")
    private List<OrderDetail> orderDetails = new ArrayList<>();

//    public Product() {}
//
//    public Product(String Name, String Description, BigDecimal Price, Double Weight, ProductCategory Category, Supplier Supplier, String ImageURL) {
//        this.Name = Name;
//        this.Description = Description;
//        this.Price = Price;
//        this.Weight = Weight;
//        this.Category = Category;
//        this.Supplier = Supplier;
//        this.ImageURL = ImageURL;
//    }
}
