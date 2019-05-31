package ro.msg.learning.shop.angular.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CategoryDTO {
    private Integer ID;
    private String name;
    private String description;
}
