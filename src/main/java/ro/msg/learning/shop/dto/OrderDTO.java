package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDTO {
    private LocalDateTime timestamp;
    private String deliveryAddress;
    private List<Integer> products;

}
