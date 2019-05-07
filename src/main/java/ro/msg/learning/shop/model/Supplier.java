package ro.msg.learning.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Supplier extends BaseEntity<Integer> {
    @Column
    private String name;
    @OneToMany(mappedBy = "supplier")
    private List<Product> products = new ArrayList<>();

//    public Supplier() {}
//
//    public Supplier(String Name) {
//        this.Name = Name;
//    }
}
