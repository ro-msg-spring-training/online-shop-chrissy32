package ro.msg.learning.shop.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class ProductCategory extends BaseEntity<Integer> {
    @Column
    private String Name;
    @Column
    private String Description;
    @OneToMany(mappedBy = "Category")
    private List<Product> products = new ArrayList<>();

    public ProductCategory() {}

    public ProductCategory(String Name, String Description) {
        this.Name = Name;
        this.Description = Description;
    }
}
