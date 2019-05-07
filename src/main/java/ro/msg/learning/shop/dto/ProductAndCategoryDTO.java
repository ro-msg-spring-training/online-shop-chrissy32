package ro.msg.learning.shop.dto;

import lombok.*;
import ro.msg.learning.shop.model.OrderDetail;
import ro.msg.learning.shop.model.Stock;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class ProductAndCategoryDTO extends BaseDTO {
    private String name;
    private String description;
    private BigDecimal price;
    private Double weight;
    private Integer category;
    private String supplier;
    private String imageURL;
    private String categoryName;
    private String categoryDescription;
    private List<Stock> stocks;
    private List<OrderDetail> orderDetails;

}
