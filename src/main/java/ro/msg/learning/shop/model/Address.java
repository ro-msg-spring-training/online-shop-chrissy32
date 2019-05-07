package ro.msg.learning.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address extends BaseEntity<Integer> {
    @Column
    private String addressCountry;
    @Column
    private String addressCity;
    @Column
    private String addressCounty;
    @Column
    private String addressStreet;
}
