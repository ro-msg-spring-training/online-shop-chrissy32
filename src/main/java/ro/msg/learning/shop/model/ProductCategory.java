package ro.msg.learning.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ProductCategory")
public class ProductCategory extends BaseEntity<Integer> {
    @Column
    private String name;
    @Column
    private String description;
    @OneToMany(mappedBy = "category")
    private List<Product> products = new ArrayList<>();
}
