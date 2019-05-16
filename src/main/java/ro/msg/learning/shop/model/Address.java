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
    @Column(nullable=false)
    private String addressCountry;
    @Column(nullable=false)
    private String addressCity;
    @Column(nullable=false)
    private String addressCounty;
    @Column(nullable=false)
    private String addressStreet;
}
