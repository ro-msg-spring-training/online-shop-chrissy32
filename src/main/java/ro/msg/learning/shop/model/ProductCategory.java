package ro.msg.learning.shop.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategory extends BaseEntity<Integer> {
    @Column(nullable=false)
    private String name;
    @Column
    private String description;
    @OneToMany(mappedBy = "category", targetEntity = Product.class)
    private List<Product> products = new ArrayList<>();

    public ProductCategory(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
