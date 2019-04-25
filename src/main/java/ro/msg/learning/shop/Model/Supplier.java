package ro.msg.learning.shop.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Supplier extends BaseEntity<Integer> {
    @Column
    private String Name;
    @OneToMany(mappedBy = "Supplier")
    private List<Product> products = new ArrayList<>();

    public Supplier() {}

    public Supplier(String Name) {
        this.Name = Name;
    }
}
