package ro.msg.learning.shop.dto;

import lombok.*;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductAndCategoryDTO extends BaseDTO<Integer> {
    private String name;
    private String description;
    private BigDecimal price;
    private Double weight;
    private Integer category;
    private Integer supplier;
    private String imageURL;
    private String categoryName;
    private String categoryDescription;
}
