package ro.msg.learning.shop.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import ro.msg.learning.shop.model.Location;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Data
public class RevenueDTO {
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "stocks", "orders", "revenues"})
    private Location location;
    private LocalDate date;
    private BigDecimal sum;

    @Override
    public String toString() {
        return location.getName() + ", " + date.getDayOfMonth() + "/" + date.getMonth() + "/" + date.getYear() + ", " + sum + "\n";
    }
}
