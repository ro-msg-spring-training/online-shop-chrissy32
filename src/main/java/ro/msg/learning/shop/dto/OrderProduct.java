package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderProduct extends BaseDTO<Integer> {
    private Integer quantity;

    public OrderProduct(Integer ID, Integer quantity) {
        this.setID(ID);
        this.quantity = quantity;
    }
}
