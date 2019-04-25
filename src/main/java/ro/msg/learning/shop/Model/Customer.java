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
public class Customer extends BaseEntity<Integer> {
    @Column
    private String FirstName;
    @Column
    private String LastName;
    @Column
    private String Username;
    @Column
    private String Password;
    @Column
    private String EmailAddress;
    @OneToMany(mappedBy = "Customer")
    private List<Order> orders = new ArrayList<>();

    public Customer() {}

    public Customer(String FirstName, String LastName, String Username, String Password, String EmailAddress) {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Username = Username;
        this.Password = Password;
        this.EmailAddress = EmailAddress;
    }
}
