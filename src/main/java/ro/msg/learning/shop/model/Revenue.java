package ro.msg.learning.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Revenue extends BaseEntity<Integer> {
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Location.class)
    @JoinColumn(name = "location", referencedColumnName = "name")
    private String location;
    @Column
    private LocalDate date;
    @Column
    private BigDecimal sum;

//    public Revenue() {}
//
//    public Revenue(Location Location, LocalDate Date, BigDecimal Sum) {
//        this.Location = Location;
//        this.Date = Date;
//        this.Sum = Sum;
//    }
}
