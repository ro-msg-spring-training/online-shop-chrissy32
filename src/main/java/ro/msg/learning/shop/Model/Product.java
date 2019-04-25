package ro.msg.learning.shop.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Product extends BaseEntity<Integer> {
    @Column
    private String Name;
    @Column
    private String Description;
    @Column
    private BigDecimal Price;
    @Column
    private Double Weight;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Category")
    private ProductCategory Category;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Supplier")
    private Supplier Supplier;
    @Column
    private String ImageURL;
    @OneToMany(mappedBy = "Product")
    private List<Stock> stocks = new ArrayList<>();
    @OneToMany(mappedBy = "Product")
    private List<OrderDetail> orderDetails = new ArrayList<>();

    public Product() {}

    public Product(String Name, String Description, BigDecimal Price, Double Weight, ProductCategory Category, Supplier Supplier, String ImageURL) {
        this.Name = Name;
        this.Description = Description;
        this.Price = Price;
        this.Weight = Weight;
        this.Category = Category;
        this.Supplier = Supplier;
        this.ImageURL = ImageURL;
    }
}
