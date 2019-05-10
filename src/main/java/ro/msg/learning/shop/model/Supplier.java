package ro.msg.learning.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

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
    @JsonIgnoreProperties("supplier")
    private List<Product> products = new ArrayList<>();
}
