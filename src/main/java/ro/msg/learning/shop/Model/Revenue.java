package ro.msg.learning.shop.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Revenue extends BaseEntity<Integer> {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Location")
    private Location Location;
    @Column
    private LocalDate Date;
    @Column
    private BigDecimal Sum;

    public Revenue() {}

    public Revenue(Location Location, LocalDate Date, BigDecimal Sum) {
        this.Location = Location;
        this.Date = Date;
        this.Sum = Sum;
    }
}
